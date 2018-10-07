package com.saripalli.musicartists.apiclient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.saripalli.musicartists.models.AlbumCover;
import com.saripalli.musicartists.models.AlbumCoverDeserializer;

/**
 * CoverArtArchiveClient
 * @author phani
 *
 */
@Service
public class CoverArtArchiveClient extends BaseClient {
	
	private static final Logger log = LoggerFactory.getLogger(BaseClient.class);
	
	public CoverArtArchiveClient() {
        super(AlbumCover.class, new AlbumCoverDeserializer());
    }
	/**
	 * Calls the coverart client
	 * http://coverartarchive.org/release-group/{albumId} which returns images of the
	 * album. It uses AlbumCoverDeserializer to parse and extract the front cover image.
	 * @param albumId id of the album
	 * @return AlbumCover object.
	 */
	public AlbumCover getCover(String albumId) {
			
		AlbumCover albumCover = null;
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://coverartarchive.org/release-group/" + albumId;					    
		
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
					new HttpEntity<>(new HttpHeaders()), String.class);
										
			albumCover = gson.fromJson(response.getBody(), AlbumCover.class);			
		} catch (HttpStatusCodeException e) {			
			log.info("Info: url has not result " + url);			
		}
						
		return albumCover;
	}
	

}
