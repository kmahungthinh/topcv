package com.example.topcv;

import com.example.topcv.common.util.Utils;
import java.util.Properties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class TopCVApplication {

  @Value("${my.email}")
  private String MY_EMAIL;

  @Value("${my.password}")
  private String MY_PASSWORD;

  public static void main(String[] args) {
    SpringApplication.run(TopCVApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper;
  }

  @Bean
  public Utils utils() {
    return new Utils();
  }

  @Bean
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    resolver.setDefaultEncoding("UTF-8");
    return resolver;
  }

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
    mailSender.setUsername(MY_EMAIL);
    mailSender.setPassword(MY_PASSWORD);
    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");
    return mailSender;
  }
}
