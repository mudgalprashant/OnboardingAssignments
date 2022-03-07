package com.newsletter.newsport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NewsportApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsportApplication.class, args);
	}

}
