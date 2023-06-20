package com.mango.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, Integer activationID) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("serbia.mango@gmail.com");
        message.setTo(to);
        message.setText("Hey! Please confirm this is your email address by entering this link: http://localhost:8080/api/passenger/activate/" + activationID);
        message.setSubject("Activate your ManGo account!");
        this.mailSender.send(message);
    }
}
