package com.demo.userapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEmailIntegrityException extends RuntimeException {

    public UserEmailIntegrityException(String message) {
        super(message);
    }

}
