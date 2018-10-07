package com.saripalli.musicartists.models;

/**
 * AlbumCover representation with getters and setters of props.
 * Consist of the main cover and short and long thumbnail.
 * 
 * @author phani
 *
 */
public class AlbumCover {
	
	private String frontCoverSrc;
	private String thumbnailSmall;
	private String thumbnailLarge;
	
	public String getFrontCoverSrc() {
		return frontCoverSrc;
	}
	
	public void setFrontCoverSrc(String frontCoverSrc) {
		this.frontCoverSrc = frontCoverSrc;
	}
	
	public String getThumbnailSmall() {
		return thumbnailSmall;
	}
	
	public void setThumbnailSmall(String thumbnailSmall) {
		this.thumbnailSmall = thumbnailSmall;
	}
	
	public String getThumbnailLarge() {
		return thumbnailLarge;
	}
	
	public void setThumbnailLarge(String thumbnailLarge) {
		this.thumbnailLarge = thumbnailLarge;
	}
	
}
