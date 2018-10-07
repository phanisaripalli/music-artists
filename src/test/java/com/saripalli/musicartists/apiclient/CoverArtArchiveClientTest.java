package com.saripalli.musicartists.apiclient;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.saripalli.musicartists.models.AlbumCover;

public class CoverArtArchiveClientTest {

	private CoverArtArchiveClient coverArtArchiveClient;
	
	@Before
	public void init() {
		coverArtArchiveClient = new CoverArtArchiveClient();
	}
	
	/**
	 * A basic test for checking if url is extracted. 
	 * Can be checked against the url string, bit it will fail
	 * if the url is changed. It should not happen ideally.
	 */
	@Test
	public void testCoverImageExists() {
		String albumId = "b76429b4-d828-4cd3-aae0-cdb33b3eac6b";
		AlbumCover albumCover = coverArtArchiveClient.getCover(albumId);
		
		String mainImageCover = albumCover.getFrontCoverSrc();
		
		assertNotNull(mainImageCover);
	}
	
	@Test
	public void testCoverImageDoesNotExist() {
		String albumId = "c2e05b2f-d9dc-3097-9524-b53391a8658a";
		AlbumCover albumCover = coverArtArchiveClient.getCover(albumId);
				
		assertNull(albumCover);
	}
}
