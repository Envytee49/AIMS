package com.example.aims.subsystem;

import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.entity.payment.RefundTransaction;
import com.example.aims.exception.payment.PaymentException;
import com.example.aims.exception.UnrecognizedException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * The {@code InterbankInterface} class is used to communicate with the
 * {@link PaymentSubsystem InterbankSubsystem} to make transaction
 * 
 * @author envytee
 * 
 */
public interface IPaymentSubsystem {

	PaymentTransaction getPaymentTransaction(Map<String, String> res)
			throws PaymentException, UnrecognizedException;

	String generateURL(int amount, String content) throws UnsupportedEncodingException;
	RefundTransaction refund(PaymentTransaction paymentTransaction) throws IOException;

}
