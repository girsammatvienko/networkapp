package com.group.networkapp.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends NetworkAppException {
    public InvalidEmailException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
