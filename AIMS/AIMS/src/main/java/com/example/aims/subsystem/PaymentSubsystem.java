package com.example.aims.subsystem;

import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.entity.payment.RefundTransaction;
import com.example.aims.exception.payment.PaymentException;
import com.example.aims.exception.UnrecognizedException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author envytee
 *
 */
public class PaymentSubsystem implements IPaymentSubsystem {

	/**
	 * Represent the controller of the subsystem
	 */
	private IPaymentSubsystem controller;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public PaymentSubsystem(IPaymentSubsystem controller) {
		this.controller = controller;
	}

	@Override
	public PaymentTransaction getPaymentTransaction(Map<String, String> res)
			throws PaymentException, UnrecognizedException {
		return controller.getPaymentTransaction(res);
	}

	@Override
	public String generateURL(int amount, String content) throws UnsupportedEncodingException {
		return controller.generateURL(amount, content);
	}

	@Override
	public RefundTransaction refund(PaymentTransaction paymentTransaction) throws IOException {
		return controller.refund(paymentTransaction);
	}
}
