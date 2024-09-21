package com.jsp.ets.exception_handler;

import com.jsp.ets.exception.InvalidStackException;
import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class StackExceptionHandler {

    private CustomResponseBuilder builder;

    @ExceptionHandler(InvalidStackException.class)
    public ResponseEntity<ErrorStructure<String>> handleInvalidStack(InvalidStackException ex) {
        return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Invalid stack entered");
    }
}
