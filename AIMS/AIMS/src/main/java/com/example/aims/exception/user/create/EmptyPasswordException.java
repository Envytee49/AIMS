package com.example.aims.exception.user.create;

import com.example.aims.exception.RuntimeException;

public class EmptyPasswordException extends RuntimeException {
    public EmptyPasswordException(String message) {
        super(message);
    }
}
