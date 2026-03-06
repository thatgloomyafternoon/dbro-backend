package com.fw.dbro.backend.configurations;

import com.fw.dbro.backend.constants.EmailConstants;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    javaMailSender.setHost(EmailConstants.HOST);
    javaMailSender.setPort(EmailConstants.PORT);
    javaMailSender.setUsername(EmailConstants.EMAIL_ADDR);
    javaMailSender.setPassword(EmailConstants.EMAIL_PSWD);
    Properties properties = javaMailSender.getJavaMailProperties();
    properties.put(EmailConstants.PROPERTY_KEY_MAIL_TRANSPORT_PROTOCOL, EmailConstants.PROPERTY_VALUE_SMTP);
    properties.put(EmailConstants.PROPERTY_KEY_MAIL_SMTP_AUTH, EmailConstants.PROPERTY_VALUE_TRUE);
    properties.put(EmailConstants.PROPERTY_KEY_MAIL_SMTP_STARTTLS_ENABLE, EmailConstants.PROPERTY_VALUE_TRUE);
    properties.put(EmailConstants.PROPERTY_KEY_MAIL_DEBUG, EmailConstants.PROPERTY_VALUE_TRUE);
    return javaMailSender;
  }

}
