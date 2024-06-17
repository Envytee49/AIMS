package com.example.aims.exception.user.create;

import com.example.aims.exception.RuntimeException;

public class EmptyUsernameException extends RuntimeException {
    public EmptyUsernameException(String message) {
        super(message);
    }
}
