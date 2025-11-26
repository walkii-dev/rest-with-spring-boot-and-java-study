package com.educational.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MathInputException extends RuntimeException {
    public MathInputException(String message) {
        super(message);
    }
}
