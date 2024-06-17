package com.example.aims.entity.payment;

import com.example.aims.entity.order.Order;
import lombok.Data;

import javax.persistence.*;

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

    public PaymentTransaction() {
    }


    public PaymentTransaction(
                              String transactionId,
                              String transactionContent,
                              int amount,
                              String createdAt,
                              String transactionNum) {
        super();
        this.transactionId = transactionId;
        this.transactionNum = transactionNum;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
        this.message = "Successful Transaction";
    }

}
