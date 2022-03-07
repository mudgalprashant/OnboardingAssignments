package com.newsletter.email.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Email.
 */
@Getter
@Setter
@NoArgsConstructor
public class Email implements Serializable {

  private String sender;
  private String receiver;
  private String subject;
  private String content;

}
