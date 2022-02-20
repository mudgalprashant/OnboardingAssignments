package com.newsletter.email.controller;

import com.newsletter.email.constants.Constant;
import com.newsletter.email.models.Email;
import com.newsletter.email.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsumerController {

  @Autowired
  EmailSenderService emailSenderService;

  @KafkaListener(topics = Constant.TOPIC,
      groupId = Constant.GROUP_ID,
      containerFactory = "kafkaListenerContainerFactory")
  public void listen(Email email) {
    emailSenderService.sendEmail(email);
  }
}
