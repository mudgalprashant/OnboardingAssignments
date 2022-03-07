package com.newsletter.subscribe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The type Subscribe application.
 */
@SpringBootApplication
@EnableFeignClients
public class SubscribeApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
		SpringApplication.run(SubscribeApplication.class, args);
	}

}
