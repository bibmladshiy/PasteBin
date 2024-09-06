package com.example.pastebin.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

//Класс, содержащий логику, необходимую для сборки сообщения, которое потом отправляется по почте
@RequiredArgsConstructor
@Slf4j
@Configuration
public class CustomMail extends SimpleMailMessage{
    private final SimpleMailMessage simpleMail = new SimpleMailMessage();
    int randomNum = ThreadLocalRandom.current().nextInt(10000,99999);
    public SimpleMailMessage sendRegMail(String sendTo) {
        simpleMail.setFrom("misha.shevluagin@yandex.ru");
        simpleMail.setTo(sendTo);
        simpleMail.setSubject("Please, use this generated code to finish your registration");
        simpleMail.setText(String.valueOf(randomNum));
        return simpleMail;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.yandex.ru");
        mailSender.setPort(465);

        mailSender.setUsername("вставить почту, с которой будет проводиться рассылка");
        mailSender.setPassword("вставить пароль для яндекс почты");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtps");

        return mailSender;
    }

}