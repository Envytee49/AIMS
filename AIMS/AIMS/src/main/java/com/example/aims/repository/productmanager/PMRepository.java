package com.example.aims.repository.productmanager;

import com.example.aims.entity.order.Order;
import com.example.aims.entity.media.Media;

import javax.persistence.RollbackException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface PMRepository {
    // TODO all the product methods...
    void approveOrder(int orderId);
    void rejectOrder(int orderId);
    List<Order> getPendingOrders();
    Order getPendingOrderById(int orderId);
    List<Media> getMedias();
    void addMedia(Media media);
    void removeMedia(int id) throws RollbackException;
    void updateMedia(Media media);
    List<Media> searchMedia(String keyword);
}
