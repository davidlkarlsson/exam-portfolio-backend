package com.david.examportfolio.exam_portfolio_backend.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestContactDTO(

        @NotBlank(message = "Name cannot be blank nor contain only whitespaces")
        @Size(message = "Must contain between 5-50 chars", min = 1, max = 50)
        String name,

        @Pattern(
                regexp = "^$|^[+]?[0-9\\s\\-\\(\\)]{5,20}$",
                message = "Must be a valid phone number or empty")
        String phone,

        @Email(message = "Must be a valid email address")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Email must be in format example@domain.com"
        )
        String email,

        String subject,

        @NotBlank(message = "Message cannot be blank nor contain only whitespaces")
        String message
) {

    public String getDisplayPhone() {
        return phone != null && !phone.trim().isEmpty() ? phone : "Not provided";
    }

    public String getEmailSubject() {
        return subject != null && !subject.trim().isEmpty() ? subject : "New contact from: " + name;
    }
}
