package com.example.aims.exception.user.create;

import com.example.aims.exception.RuntimeException;

public class EmptyEmailException extends RuntimeException {
    public EmptyEmailException(String message) {
        super(message);
    }
}
