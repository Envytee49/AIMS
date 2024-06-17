package com.example.aims.exception.user.create;

import com.example.aims.exception.RuntimeException;

public class EmptyPhoneException extends RuntimeException {
    public EmptyPhoneException(String message) {
        super(message);
    }
}
