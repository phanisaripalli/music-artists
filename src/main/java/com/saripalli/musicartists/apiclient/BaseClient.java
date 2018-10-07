package com.saripalli.musicartists.apiclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;


public class BaseClient {	
	protected Gson gson;
	
	BaseClient(Class modelClass, JsonDeserializer<?> deserializer) {		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(modelClass, deserializer);
	    this.gson = gsonBuilder.create();	    
	}
		
}
