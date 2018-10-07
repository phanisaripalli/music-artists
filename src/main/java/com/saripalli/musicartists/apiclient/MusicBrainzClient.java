package com.saripalli.musicartists.apiclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.saripalli.musicartists.models.Artist;
import com.saripalli.musicartists.models.ArtistDeserializer;
import com.saripalli.musicartists.models.BaseModel;
import com.saripalli.musicartists.models.ErrorModel;
import com.saripalli.musicartists.models.NotFoundModel;

/**
 * MusicBrainzClient
 * @author phani
 *
 */
@Service
public class MusicBrainzClient extends BaseClient {
		
	public MusicBrainzClient() {		
		super(Artist.class, new ArtistDeserializer());
	}
	
	/**
	 * Calls the music brainz end point. Takes the result and parses
	 * 	based on ArtistDeserializer.
	 * @param mbid mbid of the artist.
	 * @return a BaseModel object. If the processing is successful the result contains
	 * 	an artist object. 
	 */
	@SuppressWarnings("removal")
	public BaseModel getMusicBrainzdata(String mbid) {				
		
	    RestTemplate restTemplate = new RestTemplate();
		String url = "http://musicbrainz.org/ws/2/artist/"+mbid+"?&fmt=json&inc=url-rels+release-groups";
		BaseModel result = null;		
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
					new HttpEntity<>(new HttpHeaders()), String.class, mbid);
					
			HttpStatus responseStatus = response.getStatusCode();			
			
			if (responseStatus.is2xxSuccessful()) {		
				Artist artistObj = gson.fromJson(response.getBody(), Artist.class);
				artistObj.setMbid(mbid);
					
				result = artistObj;
			}
		} catch(RestClientException rcex) {
			NotFoundModel nodeFoundModel = new NotFoundModel();
			nodeFoundModel.setMessage("mbid " + mbid + " is not found.");
			result = nodeFoundModel;
		} catch (Exception e) {			
			ErrorModel erroModel = new ErrorModel();			
			result = erroModel;			
		}
						
		return result;
		
	}

	private ErrorModel getErrorModel() {
		ErrorModel errorM = new ErrorModel();		
		
		return errorM;
	}
			

}
