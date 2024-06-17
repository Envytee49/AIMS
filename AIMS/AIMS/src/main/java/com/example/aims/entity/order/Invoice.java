package com.example.aims.entity.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Invoice {

    private List<OrderMedia> medias;
    private int subtotal;
    private int subtotalWithVAT;
    private int totalAmount;
    private int deliveryFee;
    private Order order;

    public Invoice(Order order) {
        this.order = order;
        medias = order.getLstOrderMedia();
        subtotal = computeSubtotal(order.getLstOrderMedia());
        subtotalWithVAT = computeSubtotalWithVAT();
        totalAmount = order.getTotalAmount();
        deliveryFee = order.getShippingFee();
    }
    private int computeSubtotalWithVAT() {
        return subtotal + subtotal / 10;
    }
    private int computeSubtotal(List<OrderMedia> lstOrderMedia) {
        return lstOrderMedia.stream()
                .mapToInt(orderMedia -> orderMedia.getMedia().getPrice() * orderMedia.getQuantity())
                .sum();
    }

}
