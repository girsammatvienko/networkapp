package com.group.networkapp.domain.exception;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends NetworkAppException {

    public PostNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
