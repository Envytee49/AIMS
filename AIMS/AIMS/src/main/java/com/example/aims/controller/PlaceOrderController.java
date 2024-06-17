package com.example.aims.controller;

import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.delivery.DeliveryInfo;
import com.example.aims.exception.RuntimeException;
import com.example.aims.utils.Utils;

import java.util.HashMap;
import java.util.List;

public class PlaceOrderController {

    private List<CartMedia> cartMediaList;

    private DeliveryInfo deliveryInfo;

    public PlaceOrderController (List<CartMedia> cartMediaList) {
        this.cartMediaList = cartMediaList;
    }

    public List<CartMedia> getCartMediaList() {
        return cartMediaList;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public int calculateShippingFee(String provinceInput) {
        double totalWeight = 0;
        int amount = 0;
        int shippingfee = 0;

        for (CartMedia cm : cartMediaList) {
            amount += cm.getPrice() * cm.getQuantity();
            totalWeight += cm.getMedia().getWeight() * cm.getQuantity();
        }

        if (provinceInput.equals("Hồ Chí Minh (TP)") || provinceInput.equals("Hà Nội (TP)")) {
            totalWeight -= 3;
            shippingfee += 22000;
        } else {
            totalWeight -= 0.5;
            shippingfee += 30000;
        }

        if (totalWeight > 0) {
            totalWeight = (int) Math.ceil(totalWeight * 2);

            shippingfee += 2500 * totalWeight;
        }

        if (amount > 1000000) {
            shippingfee = Math.max(shippingfee - 25000, 0);
        }

        return shippingfee;
    }

    public void processDeliveryInfo(HashMap<String, String> info) throws RuntimeException {
        Utils.processUserInfo(info.get("name"), info.get("email"),info.get("address"),info.get("phone"));
        deliveryInfo = DeliveryInfo.builder()
                .name(info.get("name"))
                .province(info.get("province"))
                .address(info.get("address"))
                .phone(info.get("phone"))
                .email(info.get("email"))
                .build();
    }

    public boolean checkRushOrderAddress() {
        return deliveryInfo.getProvince().equals("Hà Nội (TP)");
    }

    public int calculateSubtotal() {
        return cartMediaList.stream()
                .mapToInt(cartMedia -> cartMedia.getMedia().getPrice() * cartMedia.getQuantity())
                .sum();
    }
}
