package org.example.onlineorder.model.payment;

import javax.persistence.*;

import lombok.Data;
import org.example.onlineorder.model.Order;

@Entity
@Data
@Table(name = "payment_transaction")
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "transaction_num")
    private String transactionNum;
    @Column(name = "transaction_content")
    private String transactionContent;
    @Column(name = "created_at")
    private String createdAt;
    private String message;
    private int amount;


}
