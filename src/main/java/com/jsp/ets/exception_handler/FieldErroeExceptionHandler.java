package com.jsp.ets.exception_handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.ets.exception.CustomFieldError;
import com.jsp.ets.utility.CustomResponseBuilder;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class FieldErroeExceptionHandler extends ResponseEntityExceptionHandler {

	private CustomResponseBuilder customResponseBuilder;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<ObjectError> objectErrors = ex.getAllErrors();

		List<CustomFieldError> errors = new ArrayList<>();

		for (ObjectError error : objectErrors) {
			FieldError fieldError = (FieldError) error;
			errors.add(CustomFieldError.create(fieldError.getField(), fieldError.getDefaultMessage()));
		}

		return customResponseBuilder.field(HttpStatus.BAD_REQUEST, "Bad Request, invalid inputs", errors);
	}

}
