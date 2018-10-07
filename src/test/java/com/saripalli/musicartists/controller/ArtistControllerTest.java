package com.saripalli.musicartists.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.saripalli.musicartists.apiclient.ArtistClient;
import com.saripalli.musicartists.apiclient.CoverArtArchiveClient;
import com.saripalli.musicartists.apiclient.DiscogsClient;
import com.saripalli.musicartists.apiclient.MusicBrainzClient;
import com.saripalli.musicartists.models.BaseModel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArtistControllerTest {
	
	@Autowired
	private ArtistController artistController;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(artistController).build();
	}
	
	/**
	 * A mock test for an mbid that is valid.
	 * @throws Exception
	 */
	@Test
	public void testSuccessMB() throws Exception {
		String mbId = "e0bba708-bdd3-478d-84ea-c706413bedab";
		String path = "/v1/artist?mbid=" + mbId; 		
				
		this.mockMvc.perform(get(path)).andExpect(status().is2xxSuccessful());		
		
	}
	
	/**
	 * A mock test for an mbid that is not valid.
	 * @throws Exception
	 */
	@Test
	public void testNotFoundMB() throws Exception {
		String mbId = "b76429b4-d828-4cd3-aae0-cdb33b3eac6b";
		String path = "/v1/artist?mbid=" + mbId; 		
		this.mockMvc.perform(get(path)).andExpect(status().is4xxClientError());
	}
}
