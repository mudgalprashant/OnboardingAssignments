package com.newsletter.subscribe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubscribeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscribeApplication.class, args);
	}

}
