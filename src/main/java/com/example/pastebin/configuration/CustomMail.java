package com.example.kal_l.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//Класс, содержащий логику, необходимую для сборки сообщения, которое потом отправляется по почте
@RequiredArgsConstructor
@Slf4j
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
    public SimpleMailMessage sendLikeMail(String sendTo, String userName, String pastaName) {
        simpleMail.setFrom("misha.shevluagin@yandex.ru");
        simpleMail.setTo(sendTo);
        simpleMail.setSubject("Someone just liked your paste!");
        simpleMail.setText("User "+userName+" liked your paste: "+pastaName);
        return simpleMail;
    }
}