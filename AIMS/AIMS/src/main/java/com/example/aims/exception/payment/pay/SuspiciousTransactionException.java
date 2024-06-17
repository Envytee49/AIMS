package com.example.aims.exception.payment.pay;

import com.example.aims.exception.payment.PaymentException;

;

public class SuspiciousTransactionException extends PaymentException {
	public SuspiciousTransactionException() {
		super("ERROR: Suspicious Transaction Report!");
	}
}
