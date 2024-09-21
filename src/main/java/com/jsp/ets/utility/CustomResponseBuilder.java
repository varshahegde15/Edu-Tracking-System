package com.jsp.ets.utility;

import com.jsp.ets.exception.CustomFieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomResponseBuilder {

    public <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message, T data) {
        return ResponseEntity.status(status).body(ResponseStructure.create(status.value(), message, data));
    }

    public <T> ResponseEntity<ErrorStructure<T>> error(HttpStatus status, String message, T rootCause) {
        return ResponseEntity.status(status).body(ErrorStructure.create(status.value(), message, rootCause));
    }

    public ResponseEntity<Object> field(HttpStatus status, String message, List<CustomFieldError> data) {
        return ResponseEntity.status(status).body(ResponseStructure.create(status.value(), message, data));
    }
}
