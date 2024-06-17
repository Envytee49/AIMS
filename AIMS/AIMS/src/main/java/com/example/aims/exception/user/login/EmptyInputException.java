package com.example.aims.exception.user.login;

import com.example.aims.exception.RuntimeException;

public class EmptyInputException extends RuntimeException {
    public EmptyInputException(String message) {
        super(message);
    }
}
