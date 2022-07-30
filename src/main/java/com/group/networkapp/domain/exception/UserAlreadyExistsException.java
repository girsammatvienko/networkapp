package com.group.networkapp.domain.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends NetworkAppException {
    public UserAlreadyExistsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
