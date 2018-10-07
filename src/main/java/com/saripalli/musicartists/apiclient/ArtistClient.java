package com.saripalli.musicartists.apiclient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.saripalli.musicartists.models.AlbumCover;
import com.saripalli.musicartists.models.Artist;
import com.saripalli.musicartists.models.Profile;
import com.saripalli.musicartists.models.BaseModel;

@Service
@CacheConfig(cacheNames = {"artists"})
public class ArtistClient {
	
	private static final Logger log = LoggerFactory.getLogger(ArtistClient.class);
		
	private MusicBrainzClient musicBrainzClient;
	private CoverArtArchiveClient coverArtClient;	
	private DiscogsClient discogsClient;
	
	@Autowired
	public ArtistClient(MusicBrainzClient musicBrainzClient, 
			CoverArtArchiveClient coverArtClient, 
			DiscogsClient discogsClient) {
		this.musicBrainzClient = musicBrainzClient;
		this.coverArtClient = coverArtClient;
		this.discogsClient = discogsClient;
	}
	
	/**
	 * Talks to MusicBrainz, after which calls Coverart and Discogs, collects 
	 * 	the results and merges.
	 * @param mbid mbid of the artist
	 * @return an object of type BaseModel  
	 */
	@Cacheable(key="#mbid")
	public BaseModel getArtistByMbid(String mbid) {		
		log.info("Received request for " + mbid);		
		BaseModel baseModel = this.musicBrainzClient.getMusicBrainzdata(mbid);		
		
		if (baseModel instanceof Artist) {			
			Artist artist = (Artist) baseModel;						
			CompletableFuture<Artist> artistFuture = updateCovers(artist);
			CompletableFuture<Artist> artistDescriptionFuture = updateDiscogs(artist);
			CompletableFuture allDoneFuture = CompletableFuture.allOf(artistFuture, artistDescriptionFuture);			
			
			try {
				allDoneFuture.get();
				artist = artistFuture.get();
			} catch (InterruptedException | ExecutionException e) {
				log.error("Error for mbid " + mbid);
				log.error(e.getMessage());			
				e.printStackTrace();
			}
			
		}		
		
		return baseModel;			
	}
	
	/**
	 * Calls the coverartclient and updates the artist with cover images 
	 * @param artist
	 * @return artist
	 */
	private CompletableFuture<Artist> updateCovers(Artist artist) {		
		return CompletableFuture.supplyAsync(() -> {
			var albums = artist.getAlbums();			
			albums.stream().forEach(album -> {
				AlbumCover albumCover = this.coverArtClient.getCover(album.getId());								
				if (albumCover != null) {					
					album.setCoverSrc(albumCover);
				}								
			});
			return artist;
		});					
	}
	
	/**
	 * Calls the discogs api and updates the artist with profile info 
	 * @param artist artist object
	 * @return artist
	 */
	private CompletableFuture<Artist> updateDiscogs(Artist artist) {
		return CompletableFuture.supplyAsync(() -> {
			Profile artistProfile = this.discogsClient.getDiscogs(artist.getProfile().getId());			
			artist.setProfile(artistProfile);
			
			return artist;
		});					
	}
		
}
