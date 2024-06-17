package com.example.aims.repository.order;

import com.example.aims.entity.order.Order;
import com.example.aims.repository.AIMSDB;

import javax.persistence.EntityManager;

public class OrderRepositoryImpl implements OrderRepository{
    @Override
    public Order createOrder(Order order) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        return em.find(Order.class, order.getId());
    }

    @Override
    public void deleteOrder(int id) {
        EntityManager em = AIMSDB.getEntityManager();
        Order order = em.find(Order.class, id);
        em.getTransaction().begin();
        em.remove(order);
        em.getTransaction().commit();
    }
}
