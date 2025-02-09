package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SbootConsumeRestApiJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootConsumeRestApiJpaApplication.class, args);
		
	}
	
	// Creating RestTemplate bean in config class ::
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}
