package com.jsp.ets.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ets.exception.BatchNotFoundByIdException;
import com.jsp.ets.exception.RatingNotFoundByIdException;
import com.jsp.ets.utility.CustomResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class BatchExceptionHandler {

	private CustomResponseBuilder builder;

	@ExceptionHandler(BatchNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleBatchNotFoundById(BatchNotFoundByIdException ex) {
		return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Batch not found by given Id");
	}
}
