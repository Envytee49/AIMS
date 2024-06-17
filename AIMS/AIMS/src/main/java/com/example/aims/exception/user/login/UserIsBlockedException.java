package com.example.aims.exception.user.login;

import com.example.aims.exception.RuntimeException;

public class UserIsBlockedException extends RuntimeException {
    public UserIsBlockedException(String message) {
        super(message);
    }
}
