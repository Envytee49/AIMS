package com.example.aims.repository.order;

import com.example.aims.entity.order.Order;

public interface OrderRepository {
    Order createOrder(Order order);

    void deleteOrder(int id);
}
