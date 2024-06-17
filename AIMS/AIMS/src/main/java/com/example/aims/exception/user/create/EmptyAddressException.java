package com.example.aims.exception.user.create;

import com.example.aims.exception.RuntimeException;

public class EmptyAddressException extends RuntimeException {
    public EmptyAddressException(String message) {
        super(message);
    }
}
