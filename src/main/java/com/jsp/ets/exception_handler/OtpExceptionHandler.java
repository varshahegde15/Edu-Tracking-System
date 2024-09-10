package com.jsp.ets.exception_handler;

import com.jsp.ets.exception.BatchNotFoundByIdException;
import com.jsp.ets.exception.InvalidOtpException;
import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class OtpExceptionHandler {
    private CustomResponseBuilder builder;

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<ErrorStructure<String>> handleInvalidOtp(InvalidOtpException ex) {
        return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Invalid otp is been entered");
    }
}
