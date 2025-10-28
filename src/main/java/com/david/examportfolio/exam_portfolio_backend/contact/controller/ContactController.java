package com.david.examportfolio.exam_portfolio_backend.contact.controller;


import com.david.examportfolio.exam_portfolio_backend.contact.dto.RequestContactDTO;
import com.david.examportfolio.exam_portfolio_backend.contact.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ContactController {

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/public/contact")
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody RequestContactDTO request) {

        emailService.sendContactEmail(request);

        return ResponseEntity.ok().build();
    }

}
