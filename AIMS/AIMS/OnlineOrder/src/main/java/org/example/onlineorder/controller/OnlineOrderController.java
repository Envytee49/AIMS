package org.example.onlineorder.controller;

import lombok.RequiredArgsConstructor;
import org.example.onlineorder.Response;
import org.example.onlineorder.model.Order;
import org.example.onlineorder.model.payment.PaymentTransaction;
import org.example.onlineorder.repository.AIMSDB;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequiredArgsConstructor
public class OnlineOrderController {


    @GetMapping("/order/{id}")
    public Response getPayment(@PathVariable int id) {

        EntityManager em = AIMSDB.getEntityManager();
        Order order = em.find(Order.class, id);
        PaymentTransaction paymentTransaction = (PaymentTransaction) em.createNativeQuery(
                "select * from payment_transaction where order_id = :id", PaymentTransaction.class
        )
                .setParameter("id", id)
                .getSingleResult();
        Response response = new Response(order, paymentTransaction);
        return response;
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable int id) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        PaymentTransaction paymentTransaction = (PaymentTransaction) em.createNativeQuery(
                        "select * from payment_transaction where order_id = :id", PaymentTransaction.class
                )
                .setParameter("id", id)
                .getSingleResult();
        em.remove(paymentTransaction);
        Order order = em.find(Order.class, id);
        System.out.println(order.getId());
        em.remove(order);
        em.getTransaction().commit();
    }



}
