package com.saripalli.musicartists.models;

import java.lang.reflect.Type;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class AlbumCoverDeserializer implements JsonDeserializer<AlbumCover> {	

	@Override
	public AlbumCover deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		AlbumCover albumCover = new AlbumCover();
		JsonObject node = json.getAsJsonObject();
		
		
		JsonArray images =  node.get("images").getAsJsonArray();		
		Stream<JsonElement> imageElements = StreamSupport.stream(images.spliterator(), false);
		
		Optional coverElement = imageElements.filter(imageElement -> {								
			String coverType = imageElement.getAsJsonObject().get("front").getAsString();			
			return coverType.equals("true");
		}).findFirst().map(elem -> {return elem;});
		
		
		if (coverElement.isPresent()) {			
			JsonElement img = (JsonElement) coverElement.get();		
			JsonObject imgObj = img.getAsJsonObject();
			String imageSrc = imgObj.get("image").getAsString();
			
			String thumbnailLarge = imgObj
					.get("thumbnails")
					.getAsJsonObject()
					.get("large")
					.getAsString();
			
			
			String thumbnailSmall = imgObj
					.get("thumbnails")
					.getAsJsonObject()
					.get("small")
					.getAsString();
						
			albumCover.setFrontCoverSrc(imageSrc);
			albumCover.setThumbnailLarge(thumbnailLarge);
			albumCover.setThumbnailSmall(thumbnailSmall);								
		} else {
			System.out.println("not present");
		}
			
		return albumCover;		
						
	}

}
