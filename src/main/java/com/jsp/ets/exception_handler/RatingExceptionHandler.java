package com.jsp.ets.exception_handler;

import com.jsp.ets.exception.RatingNotFoundByIdException;
import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class RatingExceptionHandler {

    private CustomResponseBuilder builder;

    @ExceptionHandler(RatingNotFoundByIdException.class)
    public ResponseEntity<ErrorStructure<String>> handleRatingNotFoundById(RatingNotFoundByIdException ex) {
        return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Rating not found by given Id");
    }
}
