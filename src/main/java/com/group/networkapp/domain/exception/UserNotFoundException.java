package com.group.networkapp.domain.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends NetworkAppException {

    public UserNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
