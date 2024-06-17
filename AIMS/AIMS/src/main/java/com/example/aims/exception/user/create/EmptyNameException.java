package com.example.aims.exception.user.create;

import com.example.aims.exception.RuntimeException;

public class EmptyNameException extends RuntimeException {
    public EmptyNameException (String message) {
        super(message);
    }
}
