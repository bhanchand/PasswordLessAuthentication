package com.okta.prasad.oktaweb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.println("Into");
		SpringApplication.run(Application.class, args);
	}
}