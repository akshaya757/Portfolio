package com.backend.backend.controllers;

import com.backend.backend.model.ContactForm;
import com.backend.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/contact")
    public ResponseEntity<String> submitContact(@RequestBody ContactForm form) {
        emailService.sendContactMail(form);
        return ResponseEntity.ok("Email sent successfully!");
    }
}
