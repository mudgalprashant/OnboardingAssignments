package com.newsletter.email;

import com.newsletter.email.runner.EmailApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The type Email application.
 */
@SpringBootApplication
@EnableFeignClients
public class EmailApplication extends EmailApplicationRunner {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(EmailApplication.class, args);
  }

}
