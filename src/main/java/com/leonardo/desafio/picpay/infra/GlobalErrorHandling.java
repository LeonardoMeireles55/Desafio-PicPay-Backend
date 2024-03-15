package com.leonardo.desafio.picpay.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        @ExceptionHandler(NoContentException.class)
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public ResponseEntity<String> error201() {
            return ResponseEntity.noContent().build();
        }

        @ExceptionHandler(UnauthorizedTransactionException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<String> error400() {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unauthorized transaction");
        }

        @ExceptionHandler(InvalidTransactionException.class)
        @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
        public ResponseEntity<String> error422() {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid transaction");
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<String> Error404(ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        @ExceptionHandler(AccessDeniedException.class)
        @ResponseStatus(HttpStatus.FORBIDDEN)
        public ResponseEntity<String> AccessDenied() {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public ResponseEntity<String> error500(Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + exception.getMessage());
        }

        @ExceptionHandler(NotificationException.class)
        @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
        public ResponseEntity<String> error522(Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + exception.getMessage());
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
            String ErrorMessage = "An error occurred while processing your request, The provided value already exists in the database.";
            return new ResponseEntity<>(ErrorMessage, HttpStatus.CONFLICT);
        }
    }