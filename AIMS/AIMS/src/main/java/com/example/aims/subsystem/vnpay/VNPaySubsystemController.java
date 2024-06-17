package com.example.aims.subsystem.vnpay;



import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.entity.payment.RefundTransaction;
import com.example.aims.exception.payment.PaymentException;
import com.example.aims.exception.UnrecognizedException;
import com.example.aims.subsystem.IPaymentSubsystem;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class VNPaySubsystemController implements IPaymentSubsystem {


	private static VNPayBoundary vnPayBoundary = new VNPayBoundary();

	@Override
	public String generateURL(int amount, String content) throws UnsupportedEncodingException {
		return vnPayBoundary.generateURL(amount, content);
	}

	@Override
	public RefundTransaction refund(PaymentTransaction paymentTransaction) throws IOException {
		return vnPayBoundary.refund(paymentTransaction);
	}

	@Override
	public PaymentTransaction getPaymentTransaction(Map<String,String> response) throws PaymentException, UnrecognizedException {
		return vnPayBoundary.getPaymentTransaction(response);
	}


}
