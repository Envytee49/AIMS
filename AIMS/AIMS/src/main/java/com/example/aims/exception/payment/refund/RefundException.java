package com.example.aims.exception.payment.refund;

import com.example.aims.exception.RuntimeException;

public class RefundException extends RuntimeException {
    public RefundException(String message) {
        super(message);
    }
}
