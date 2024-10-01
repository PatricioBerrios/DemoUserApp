package com.demo.userapp.exception;

import com.demo.userapp.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    MessageUtil messageUtil;

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserEmailIntegrityException.class)
    public ResponseEntity<?> handleCustomUserEmailIntegrityException(UserEmailIntegrityException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(messageUtil.getMessage(ex.getMessage()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException e) {
        log.error("Error in the database access. Details: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(messageUtil.getMessage("Ha ocurrido un error inesperado. Por favor intentelo más tarde."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        log.error("Unexpected error. Details: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(messageUtil.getMessage("Ha ocurrido un error inesperado. Por favor intentelo más tarde."));
    }

}
