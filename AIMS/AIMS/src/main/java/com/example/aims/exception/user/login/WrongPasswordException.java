package com.example.aims.exception.user.login;

import com.example.aims.exception.RuntimeException;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException(String message) {
        super(message);
    }
}
