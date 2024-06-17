package com.example.aims.repository.payment;

import com.example.aims.entity.payment.PaymentTransaction;

public interface PaymentRepository {
    void saveTransaction(PaymentTransaction transaction);


}
