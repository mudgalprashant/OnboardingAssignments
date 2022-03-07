package com.newsletter.email.runner;

import com.newsletter.email.EmailApplication;
import com.newsletter.email.constants.Constant;
import com.newsletter.email.models.Email;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * The type Email application runner.
 */
@AllArgsConstructor
@NoArgsConstructor
public class EmailApplicationRunner implements ApplicationRunner {

  @Autowired
  private KafkaTemplate<String, Email> kafkaTemplate;

  /**
   * Send email.
   *
   * @param email the email
   */
  public void sendEmail(Email email) {
    kafkaTemplate.send(Constant.TOPIC, email);
  }

  @Override
  @Payload
  public void run(ApplicationArguments args) {
  }


}
