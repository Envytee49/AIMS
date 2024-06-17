package com.example.aims.exception.user.create;

import com.example.aims.exception.RuntimeException;

public class UsernameExistException extends RuntimeException {
    public UsernameExistException(String message) {
        super(message);
    }
}
