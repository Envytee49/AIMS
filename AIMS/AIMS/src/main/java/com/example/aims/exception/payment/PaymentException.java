package com.example.aims.exception.payment;

import com.example.aims.exception.RuntimeException;

;

public class PaymentException extends RuntimeException {
	public PaymentException(String message) {
		super(message);
	}
}
