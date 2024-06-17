package org.example.onlineorder.model.delivery;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.onlineorder.model.Order;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "delivery_info")
public class DeliveryInfo {
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
    @JsonIgnore
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
