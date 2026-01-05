package com.david.examportfolio.exam_portfolio_backend.contact.exception;

public class EmailSendFailException extends RuntimeException {

    public EmailSendFailException(String message) {
        super(message);
    }

    public EmailSendFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
