package com.newsletter.email;

import com.newsletter.email.runner.EmailApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailApplication extends EmailApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(EmailApplication.class, args);
  }
	
}
