package com.newsletter.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The type Subscription application.
 */

@EnableFeignClients
@SpringBootApplication
public class SubscriptionApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
		SpringApplication.run(SubscriptionApplication.class, args);
	}

}
