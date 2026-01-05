package com.david.examportfolio.exam_portfolio_backend.advice;

import com.david.examportfolio.exam_portfolio_backend.contact.exception.EmailSendFailException;
import com.david.examportfolio.exam_portfolio_backend.project.exception.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (ProjectNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleProjectNotFoundException(ProjectNotFoundException exception) {

        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler (EmailSendFailException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailSendFailException(EmailSendFailException exception) {

        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {

        List<ValidationError> errorDetailList = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .toList();

        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorDetailList
        );
        return ResponseEntity.badRequest().body(response);
    }
}
