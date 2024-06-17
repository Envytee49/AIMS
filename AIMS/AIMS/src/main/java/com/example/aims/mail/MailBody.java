package com.example.aims.mail;

import com.example.aims.entity.order.Invoice;
import com.example.aims.entity.payment.PaymentTransaction;

public class MailBody {
    public static String createMailBody(Invoice invoice, PaymentTransaction transaction) {
        StringBuilder emailBody = new StringBuilder();

        emailBody.append("Dear " + invoice.getOrder().getDeliveryInfo().getName() + " ,\n\n")
                .append("Thank you for your order. Here are the details of your recent transaction:\n\n")
                .append("Transaction Details:\n")
                .append("Transaction ID: ").append(transaction.getTransactionId()).append("\n")
                .append("Transaction Number: ").append(transaction.getTransactionNum()).append("\n")
                .append("Transaction Content: ").append(transaction.getTransactionContent()).append("\n")
                .append("Created At: ").append(transaction.getCreatedAt()).append("\n")
                .append("Message: ").append(transaction.getMessage()).append("\n")
                .append("Invoice Details:\n")
                .append("Subtotal: ").append(invoice.getSubtotal()).append(" VND").append("\n")
                .append("Subtotal with VAT: ").append(invoice.getSubtotalWithVAT()).append(" VND").append("\n")
                .append("Delivery Fee: ").append(invoice.getDeliveryFee()).append(" VND").append("\n\n")
                .append("Total Amount: ").append(invoice.getTotalAmount()).append(" VND").append("\n\n")
        .append("http://localhost:5173/order/" +transaction.getId() );
//                .append("Order Details:\n")
//                .append(order.toString()).append("\n")  // Assuming Order class has a meaningful toString method
//                .append("Ordered Items:\n");
        return emailBody.toString();
    }
}
