package com.example.aims.exception.user.login;

import com.example.aims.exception.RuntimeException;

public class NonExistUserException extends RuntimeException {
    public NonExistUserException(String message) {
        super(message);
    }
}
