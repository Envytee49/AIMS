package com.example.aims.exception.user;

import com.example.aims.exception.RuntimeException;

public class IncorrectOldPasswordException extends RuntimeException {
    public IncorrectOldPasswordException() {
        super("Incorrect Old Password");
    }
}
