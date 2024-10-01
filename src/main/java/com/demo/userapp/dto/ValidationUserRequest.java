package com.demo.userapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationUserRequest {

    private boolean valid;
    private String message;

    public static ValidationUserRequest success() {
        return new ValidationUserRequest(true, null);
    }

    public static ValidationUserRequest failure(String message) {
        return new ValidationUserRequest(false, message);
    }

}
