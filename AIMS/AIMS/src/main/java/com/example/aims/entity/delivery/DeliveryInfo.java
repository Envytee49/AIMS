package com.example.aims.entity.delivery;

import com.example.aims.entity.order.Order;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "delivery_info")
public class DeliveryInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_info_id")
    private int id;
    private String name;
    private String phone;
    private String email;
    private String province;
    private String address;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public DeliveryInfo(int id, String name, String phone, String email, String province, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.province = province;
        this.address = address;
    }
}
