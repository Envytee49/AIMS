package com.example.aims.entity.order;

import com.example.aims.constant.OrderState;
import com.example.aims.entity.AbstractEntity;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.delivery.DeliveryInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;
    private int shippingFee;
    private int totalAmount;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private DeliveryInfo deliveryInfo;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMedia> lstOrderMedia;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    private OrderState orderState;

    public Order(List<CartMedia> cartMediaList, int shippingFee, DeliveryInfo deliveryInfo) {
        //TODO
        this.shippingFee = shippingFee;
        this.totalAmount = computeTotalAmount(cartMediaList);
        this.deliveryInfo = deliveryInfo;
        this.deliveryInfo.setOrder(this);
        this.lstOrderMedia = getLstOrderMedia(cartMediaList);
        this.orderState = OrderState.PENDING;
    }
    private int computeTotalAmount(List<CartMedia> cartMediaList) {
        int subTotal = cartMediaList.stream()
                .mapToInt(cartMedia -> cartMedia.getQuantity() * cartMedia.getPrice()).sum();
        return (int) (subTotal * 1.1 + this.shippingFee);
    }
    private List<OrderMedia> getLstOrderMedia(List<CartMedia> cartMediaList) {
        List<OrderMedia> lstOrderMedia = cartMediaList.stream().map(cartMedia -> {
            OrderMedia orderMedia = new OrderMedia();
            orderMedia.setQuantity(cartMedia.getQuantity());
            orderMedia.setOrder(this);
            orderMedia.setMedia(cartMedia.getMedia());
            return orderMedia;
        }).toList();
        return lstOrderMedia;
    }

}