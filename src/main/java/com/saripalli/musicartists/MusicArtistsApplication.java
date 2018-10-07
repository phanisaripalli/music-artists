package com.saripalli.musicartists;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MusicArtistsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicArtistsApplication.class, args);
	}
}
