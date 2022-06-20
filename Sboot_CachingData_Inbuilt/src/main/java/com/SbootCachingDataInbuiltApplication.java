package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbootCachingDataInbuiltApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootCachingDataInbuiltApplication.class, args);
		
		System.out.println("SpringData Inbuilt Caching running !");
	}

}
