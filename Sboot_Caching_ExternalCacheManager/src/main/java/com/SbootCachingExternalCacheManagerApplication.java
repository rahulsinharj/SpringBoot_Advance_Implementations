package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbootCachingExternalCacheManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootCachingExternalCacheManagerApplication.class, args);
		
		System.out.println("Spring EhCache Caching running !");
	}

}
