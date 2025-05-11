package com.backend.backend.service;

import com.backend.backend.model.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendContactMail(ContactForm form) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("your email @gmail.com");
        message.setSubject("New Contact Message from " + form.getName());
        message.setText("Email: " + form.getEmail() + "\n\nMessage:\n" + form.getMessage());
        mailSender.send(message);
    }
}
