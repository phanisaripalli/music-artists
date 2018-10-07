package com.saripalli.musicartists.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ArtistDeserializer implements JsonDeserializer<Artist> {	

	@Override
	public Artist deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject node = json.getAsJsonObject();
		
		Artist artist = new Artist();
		
		String name = node.get("name").getAsString();
		String gender = node.get("gender").getAsString();
		String artistType = node.get("type").getAsString();
		String disambiguation = node.get("disambiguation").getAsString();		
		
		artist.setName(name);
		artist.setGender(gender);
		artist.setType(artistType);
		artist.setDescription(disambiguation);
		artist.setAlbums(getAlbums(node));
		getProfile(node);		
		artist.setProfile(getProfile(node));
		return artist;
	}
	
	private List<Album> getAlbums(JsonElement json) {
		List<Album> albums = new ArrayList<Album>();
		JsonObject node = json.getAsJsonObject();		
		JsonArray releaseGroups = node.get("release-groups").getAsJsonArray();
		releaseGroups.forEach(releaseGroup ->{
			String id = releaseGroup.getAsJsonObject().get("id").getAsString();			
			String title = releaseGroup.getAsJsonObject().get("title").getAsString();
			
			Album album = new Album();
			album.setId(id);
			album.setTitle(title);
			album.setCoverSrc(new AlbumCover());
			albums.add(album);			
		});		
		
		return albums;
				
	}		
	
	private Profile getProfile(JsonElement json) {
		Profile artistDescription =  new Profile();
		JsonObject node = json.getAsJsonObject();		
		JsonArray relations = node.get("relations").getAsJsonArray();
		
		Stream<JsonElement> relationElements = StreamSupport.stream(relations.spliterator(), false);
		
		Optional coverElement = relationElements.filter(relationElement -> {								
			String relationType = relationElement
					.getAsJsonObject()
					.get("type")
					.getAsString();
			
			return relationType.equals("discogs");
		}).findFirst().map(elem -> {
			return elem;
			}
		);
		
		if (coverElement.isPresent()) {			
			JsonElement discog = (JsonElement) coverElement.get();				
			JsonObject discogObj = discog.getAsJsonObject();			
			
			String discogRes = discogObj
					.get("url")
					.getAsJsonObject()
					.get("resource")
					.getAsString();
			
			String discogId = discogRes.substring(discogRes.lastIndexOf('/') + 1);						
			artistDescription.setId(discogId);
											
		} else {
			System.out.println("not present");
		}
		
		return artistDescription;
		
	}

}
