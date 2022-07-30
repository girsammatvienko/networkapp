package com.group.networkapp.domain.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class NetworkAppException extends RuntimeException {
    private HttpStatus httpStatus;
    public NetworkAppException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
