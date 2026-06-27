package com.quickbite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResponseNotFound(ResourceNotFoundException ex){
        return ex.getMessage();
    }
    @ExceptionHandler(InvalidOrderStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleInvalidOrderStatus(InvalidOrderStatusException ex){
        return ex.getMessage();
    }
}
