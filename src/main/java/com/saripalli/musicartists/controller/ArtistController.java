package com.saripalli.musicartists.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saripalli.musicartists.apiclient.ArtistClient;
import com.saripalli.musicartists.models.Artist;
import com.saripalli.musicartists.models.BaseModel;
import com.saripalli.musicartists.models.NotFoundModel;

/*
 * Rest controller for artist
 */
@RestController
@RequestMapping("/v1")
public class ArtistController {
	
	
	private ArtistClient artistClient;
	
	@Autowired
	public ArtistController(ArtistClient artistClient) {
		this.artistClient = artistClient;
	}
			
	/*
	 * This is kind of base end point.
	 */
	@GetMapping("")
	public String home() {
		String message = "Welcome. ";
		message += "Use the format mbid=[mbid]";
		
		return message;
	}
	
	/**
	 * REST endpoint for the artist details
	 * @param mbid mbid of the artist
	 * @return atrist basic details, albums with titles and images, and profile info
	 * 			from discogs
	 */
	@RequestMapping(value="artist", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BaseModel> getArtistData(@RequestParam("mbid") String mbid) {				
		BaseModel baseModel = artistClient.getArtistByMbid(mbid);			
		ResponseEntity<BaseModel> result = null;		
			
		if (baseModel instanceof Artist) {
			result = new ResponseEntity<BaseModel>(baseModel, HttpStatus.OK);
		} else if (baseModel instanceof NotFoundModel) {
			result = new ResponseEntity<BaseModel>(baseModel, HttpStatus.NOT_FOUND);
		} else {
			result = new ResponseEntity<BaseModel>(baseModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return result;		
			    
	}
}
