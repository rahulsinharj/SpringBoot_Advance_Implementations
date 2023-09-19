package com.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerSendingListApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerSendingListApplication.class, args);

		System.out.println("#=====Kafka Producer Application started !=====#");
	}

}
