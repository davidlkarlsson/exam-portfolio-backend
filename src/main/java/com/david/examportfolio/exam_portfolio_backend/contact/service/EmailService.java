package com.david.examportfolio.exam_portfolio_backend.contact.service;

import com.david.examportfolio.exam_portfolio_backend.contact.dto.RequestContactDTO;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String resendApiKey;

    @Value("${recipent}")
    private String recipient;


    public void sendContactEmail(RequestContactDTO requestContactDTO) {

        Resend resend = new Resend(resendApiKey);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Portfolio <onboarding@resend.dev>")
                .to(recipient)
                .subject(requestContactDTO.getEmailSubject())
                .html(
                        "<strong>New contact from your Portfolio form:</strong><br><br>" +
                        "<strong>Name:</strong> " + requestContactDTO.name() + "<br>" +
                        "<strong>Phone:</strong> " + requestContactDTO.getDisplayPhone() + "<br>" +
                        "<strong>Email:</strong> " + requestContactDTO.email() + "<br>" +
                        "<strong>Message:</strong> " + requestContactDTO.message()
                )
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println("Email sent with ID: " + data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }


    }
}
