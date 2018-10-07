package com.saripalli.musicartists.models;

/**
 * Album representation with getters and setters of props.
 * @author phani
 *
 */
public class Album {
	
	private String id;
	private String title;
	private AlbumCover albumCover;	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public AlbumCover getCoverSrc() {
		return this.albumCover;
	}
	
	public void setCoverSrc(AlbumCover albumCover) {
		this.albumCover = albumCover;
	}

}
