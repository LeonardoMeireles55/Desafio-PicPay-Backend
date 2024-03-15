package com.leonardo.desafio.picpay.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalErrorHandling {
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }

    }

    public static class NoContentException extends RuntimeException {
        public NoContentException() {
            super();
        }
    }

    public static class DataIntegrityViolationException extends RuntimeException {
        public DataIntegrityViolationException() {
            super();
        }
    }

    public static class InvalidTransactionException extends RuntimeException {

        public InvalidTransactionException() {
            super();
        }
    }

    public static class UnauthorizedTransactionException extends RuntimeException {
        public UnauthorizedTransactionException() {
            super();
        }
    }

    public static class NotificationException extends RuntimeException {
        public NotificationException(String message) {
            super(message);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Invalid request payload. Please check the request body and try again."); 
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = "Invalid field: " + ex.getFieldError().getField() + " - "
                + ex.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }

    @ExceptionHandler({ UnauthorizedTransactionException.class, InvalidTransactionException.class })
    public ResponseEntity<String> handleTransactionExceptions(RuntimeException ex) {
        HttpStatus status = (ex instanceof UnauthorizedTransactionException) ? HttpStatus.BAD_REQUEST
                : HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status).body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Void> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = "An error occurred while processing your request. The provided value already exists in the database.";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getMessage());
    }
}