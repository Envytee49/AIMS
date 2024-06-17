package com.example.aims.controller;

import com.example.aims.entity.order.Order;
import com.example.aims.repository.productmanager.PMRepositoryImpl;

import java.util.List;

public class PendingOrdersController {
    private PMRepositoryImpl pmRepository;

//    private List<Order> orderList;

    public PendingOrdersController() {
        this.pmRepository = new PMRepositoryImpl();
    }

    public List<Order> getPendingOrders() {
        return pmRepository.getPendingOrders();
    }

    public void approveOrder(Order order) {
        pmRepository.approveOrder(order.getId());
    }

    public void rejectOrder(Order order) {
        pmRepository.rejectOrder(order.getId());
    }

}
