package com.saripalli.musicartists.models;

import java.util.List;

/**
 * Artist representation.
 * Has mbid, name, gender, list of albums, and profile info.
 * 
 * @author phani
 *
 */
public class Artist extends BaseModel {

		private String mbid;
		private String name;		
		private String gender;
		private String type;
		private String disambiguation;
		private List<Album> albums;
		private Profile profile;					

		public String getMbid() {
			return mbid;
		}
		
		public void setMbid(String mbid) {
			this.mbid = mbid;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		public String getDisambiguation() {
			return disambiguation;
		}
		
		public void setDescription(String disambiguation) {
			this.disambiguation = disambiguation;
		}
		
		public List<Album> getAlbums() {
			return albums;
		}
		
		public void setAlbums(List<Album> albums) {
			this.albums = albums;
		}
		
		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile artistDescription) {
			this.profile = artistDescription;
		}
}
