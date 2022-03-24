package com.newsletter.email.controller;

import com.newsletter.email.constants.Constant;
import com.newsletter.email.models.Email;
import com.newsletter.email.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

/**
 * The type Consumer controller.
 */
@Component
public class ConsumerController {

  /**
   * The Email sender service.
   */
  @Autowired
  EmailSenderService emailSenderService;

  /**
   * Listen.
   *
   * @param email the email
   */
  @KafkaListener(topics = Constant.TOPIC,
      groupId = Constant.GROUP_ID,
      containerFactory = Constant.KAFKA_LISTENER_CONTAINER_FACTORY)
  public void listen(Email email) {
    emailSenderService.sendEmail(email);
  }
}
