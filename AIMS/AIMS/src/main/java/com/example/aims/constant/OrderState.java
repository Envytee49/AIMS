package com.example.aims.constant;

public enum OrderState {
    /**
     * PENDING : using finish paying order
     * DECLINED: PM rejects the order
     * DELIVERING: PM approves the order
     * DELIVERED: order arrive (future work)
     */
    PENDING, DECLINED, DELIVERING, DELIVERED
}
