package com.newsletter.email.controller;

import com.newsletter.email.config.KafkaProducerConfig;
import com.newsletter.email.constants.Constant;
import com.newsletter.email.dto.EmailDto;
import com.newsletter.email.mapper.EmailMapper;
import com.newsletter.email.models.Email;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/newsletter")
public class ProducerController {


  private KafkaProducerConfig producerConfig = new KafkaProducerConfig();

  @Autowired
  private KafkaTemplate<String, Email> kafkaTemplate;

  private final EmailMapper emailMapper;

  @PostMapping("/publish")
  public EmailDto publish(@RequestBody EmailDto emailDto) {
    Email email = emailMapper.dtoToEmail(emailDto);
    kafkaTemplate.send(Constant.TOPIC, email.getReceiver(), email);
    return emailMapper.emailToDto(email);
  }
}
