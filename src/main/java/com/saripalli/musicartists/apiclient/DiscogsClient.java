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
import com.google.gson.Gson;
import com.saripalli.musicartists.models.Profile;

/**
 * Discogs client
 * @author phani
 *
 */
@Service
public class DiscogsClient {
	
	private static final Logger log = LoggerFactory.getLogger(DiscogsClient.class);

	/**
	 * Calls the discogs end point
	 * https://api.discogs.com/artists/{id} and converts the result to Profile 
	 *  
	 * @param id of the artist 
	 * @return  artistDescription - object after parsing the json
	 */
	public Profile getDiscogs(String id) {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.discogs.com/artists/" + id;
		Profile artistDescription = new Profile();
		
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
					new HttpEntity<>(new HttpHeaders()), String.class);
									
			Gson gson = new Gson();
			artistDescription = gson.fromJson(response.getBody(), Profile.class);			
		} catch (HttpStatusCodeException e) {			
			log.error("Error for url " + url);
		}
						
		return artistDescription;
	}
}
