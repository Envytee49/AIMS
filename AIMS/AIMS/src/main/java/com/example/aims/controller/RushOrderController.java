package com.example.aims.controller;

import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.delivery.DeliveryInfo;
import com.example.aims.exception.RushDeliveryAddressException;

import java.util.List;

import java.util.HashMap;

public class RushOrderController {

    public PlaceOrderController placeOrderController;

    public RushOrderController(PlaceOrderController placeOrderController) {
        this.placeOrderController = placeOrderController;
    }

    public void processRushDeliveryInfo(HashMap<String, String> rushDeliveryInfo) throws RushDeliveryAddressException {
        if (!validateDeliveryInfo(rushDeliveryInfo.get("DeliveryInstruction"))) {
            throw new RushDeliveryAddressException("Delivery Instruction should not be blank");
        }
    }

    public boolean validateDeliveryInfo(String deliveryInstruction) {
        if (deliveryInstruction == null)
            return false;
        if (deliveryInstruction.trim().length() == 0)
            return false;
        return true;
    }

    public int[] calculateRushShippingFee() {
        List<CartMedia> cartMediaList = placeOrderController.getCartMediaList();
        DeliveryInfo deliveryInfo = placeOrderController.getDeliveryInfo();

        double[] totalWeight = {0, 0};
        int amount = 0;
        int[] shippingfee = {0, 0};

        for (CartMedia cm : cartMediaList) {
            if (cm.getMedia().isRushOrderAvailable()) {
                shippingfee[1] += 10000;
                totalWeight[1] += cm.getQuantity() * cm.getMedia().getWeight();
            } else {
                amount += cm.getPrice() * cm.getQuantity();
                totalWeight[0] += cm.getQuantity() * cm.getMedia().getWeight();
            }
        }

        if (deliveryInfo.getProvince().equals("Hồ Chí Minh (TP)") || deliveryInfo.getProvince().equals("Hà Nội (TP)")) {
            totalWeight[0] -= 3;
            shippingfee[0] += 22000;

            totalWeight[1] -= 3;
            shippingfee[1] += 22000;
        } else {
            totalWeight[0] -= 0.5;
            shippingfee[0] += 30000;

            totalWeight[1] -= 0.5;
            shippingfee[1] += 30000;
        }

        if (totalWeight[0] > 0) {
            totalWeight[0] = Math.ceil(totalWeight[0] * 2);

            shippingfee[0] += 2500 * totalWeight[0];
        }

        if (totalWeight[1] > 0) {
            totalWeight[1] = Math.ceil(totalWeight[1] * 2);

            shippingfee[1] += 2500 * totalWeight[1];
        }

        if (amount > 1000000) {
            shippingfee[0] = Math.max(shippingfee[0] - 25000, 0);
        }

        return shippingfee;
    }
}
