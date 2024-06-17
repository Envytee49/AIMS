package com.example.aims.repository.payment;

import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.repository.AIMSDB;

import javax.persistence.EntityManager;

public class PaymentRepositoryImpl implements PaymentRepository{
    @Override
    public void saveTransaction(PaymentTransaction transaction) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
    }
}
