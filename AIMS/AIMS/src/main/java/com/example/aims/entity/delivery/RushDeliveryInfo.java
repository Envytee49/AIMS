package com.example.aims.entity.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rush_delivery_info")
public class RushDeliveryInfo extends DeliveryInfo{
    private String deliveryTime;
    private String deliveryInstruction;

    public RushDeliveryInfo(DeliveryInfo deliveryInfo, String deliveryTime, String deliveryInstruction) {
        super(deliveryInfo.getId(),
            deliveryInfo.getName(),
            deliveryInfo.getPhone(),
            deliveryInfo.getEmail(),
            deliveryInfo.getProvince(),
            deliveryInfo.getAddress());

        this.deliveryTime = deliveryTime;
        this.deliveryInstruction = deliveryInstruction;
    }
}
