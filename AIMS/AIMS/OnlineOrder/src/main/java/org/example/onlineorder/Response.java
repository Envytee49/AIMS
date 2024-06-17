package org.example.onlineorder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.onlineorder.model.Order;
import org.example.onlineorder.model.payment.PaymentTransaction;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Order order;
    private PaymentTransaction paymentTransaction;
}
