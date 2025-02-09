package com.emailapi;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbootEmalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootEmalApiApplication.class, args);
		
		System.out.println("Sboot_EmailApi running ! "+new Date());
	}

}
