package com.example.aims.exception.media;

import com.example.aims.exception.RuntimeException;

public class NotEnoughMediaException extends RuntimeException {

    public NotEnoughMediaException(String message) {
        super(message);
    }
}
