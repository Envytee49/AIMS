package com.example.aims.exception.payment.pay;

import com.example.aims.exception.payment.PaymentException;

;

public class TransactionReverseException extends PaymentException {

    public TransactionReverseException() {
        super("ERROR: Island transaction (The customer has been deducted money at the Bank but the GD has not been completed in VNPAY)!");
    }

}
