package com.jsp.ets.exception_handler;

import com.jsp.ets.exception.EmailSendException;
import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@AllArgsConstructor
public class EmailSendExceptionHandler {
    private CustomResponseBuilder builder;

    @ExceptionHandler(EmailSendException.class)
    public ResponseEntity<ErrorStructure<String>> emailSendException(EmailSendException ex) {
        return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Could not send the email");
    }
}
