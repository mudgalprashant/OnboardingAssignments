package com.newsletter.email.services;

import com.newsletter.email.dto.EmailDto;
import com.newsletter.email.models.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
public class EmailSenderService {
  @Value("${spring.mail.username}")
  private String mailUsername;

  /**
   * The Mail password.
   */
  @Value("${spring.mail.password}")
  private String mailPassword;

  /**
   * The Mail host.
   */
  @Value("${spring.mail.host}")
  private String mailHost;

  /**
   * The Mail port.
   */
  @Value("${spring.mail.port}")
  private int mailPort;

  public void sendEmail(Email email) {
    final Properties prop = new Properties();
    prop.put("mail.smtp.host", mailHost);
    prop.put("mail.smtp.port", mailPort);
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.ssl.trust", mailHost);
    prop.put("mail.smtp.socketFactory.port", "465");
    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    prop.put("mail.transport.protocol", "smtp");
    prop.put("mail.smtp.starttls.enable", "true");

    final Session session = Session.getInstance(prop,
        new javax.mail.Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(mailUsername, mailPassword);
          }
        });
    try {
      final Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(mailUsername));
      message.setRecipients(
          Message.RecipientType.TO,
          InternetAddress.parse(email.getReceiver())
      );
      message.setSubject(email.getSubject());
      message.setText(email.getContent());

      Transport.send(message);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
