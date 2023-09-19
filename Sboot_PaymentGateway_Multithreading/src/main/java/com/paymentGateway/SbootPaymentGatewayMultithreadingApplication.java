package com.paymentGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbootPaymentGatewayMultithreadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootPaymentGatewayMultithreadingApplication.class, args);

		System.out.println("PaymentGatewayMultithreadingApplication is running !");
	}

}
