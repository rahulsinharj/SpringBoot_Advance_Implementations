package com.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConsumerReceivingListApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerReceivingListApplication.class, args);

		System.out.println("#=====Kafka Consumer Application started !=====#");
	}

}
