package com.saripalli.musicartists.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Profile of the artist. 
 * Consist of the profile description, id and
 * 	a list of urls of the artist (for e.g. facebook, homepage). 
 * 
 * 
 * @author phani
 *
 */
public class Profile {

	@SerializedName(value="profile")
	private String description;
	private List<String> urls;
	private String id;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String profile) {
		this.description = profile;
	}
	
	public List<String> getUrls() {
		return urls;
	}
	
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
