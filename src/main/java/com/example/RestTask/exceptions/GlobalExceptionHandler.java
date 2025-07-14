package com.example.RestTask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

     @ExceptionHandler(ResponseStatusException.class)
     public ResponseEntity<?> handleResourceNotFound(ErrorResponse ex) {
         ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), "Resource not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
     }
}
