package com.example.aims.repository.productmanager;

import com.example.aims.constant.OrderState;
import com.example.aims.entity.order.Order;
import com.example.aims.entity.media.*;
import com.example.aims.repository.AIMSDB;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.List;

public class PMRepositoryImpl implements PMRepository {
    // TODO implement all these methods
    @Override
    public void approveOrder(int orderId) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        Order order = em.find(Order.class, orderId);
        order.setOrderState(OrderState.DELIVERING);
        em.persist(order);
        em.getTransaction().commit();
    }

    @Override
    public void rejectOrder(int orderId) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        Order order = em.find(Order.class, orderId);
        order.setOrderState(OrderState.DECLINED);
        em.persist(order);
        em.getTransaction().commit();
    }

    @Override
    public List<Order> getPendingOrders() {
        EntityManager em = AIMSDB.getEntityManager();
        List<Order> orders = em
                .createQuery("select o from Order o where o.orderState = :orderState", Order.class)
                .setParameter("orderState", OrderState.PENDING)
                .getResultList();
        return orders;
    }

    @Override
    public Order getPendingOrderById(int orderId) {
        EntityManager em = AIMSDB.getEntityManager();
        Order order = em.find(Order.class, orderId);
        return order;

    }

    @Override
    public List<Media> getMedias() {
        EntityManager em = AIMSDB.getEntityManager();
        List<Media> mediaList = em.createQuery("select m from Media m", Media.class).getResultList();
        mediaList.forEach(em::refresh);
        return mediaList;
    }

    @Override
    public void addMedia(Media media) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        em.persist(media);
        em.getTransaction().commit();
    }

    @Override
    public void removeMedia(int id) throws RollbackException {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        Media media = em.find(Media.class, id);
        em.remove(media);
        em.getTransaction().commit();
    }

    public void  updateMedia(Media media) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();

        // Retrieve the media entity from the database
        Media m = em.find(Media.class, media.getId());
        if (m != null) {
            // Update common fields
            m.setTitle(media.getTitle());
            m.setPrice(media.getPrice());
            m.setQuantity(media.getQuantity());
            m.setImageURL(media.getImageURL());
            m.setWeight(media.getWeight());
            m.setValue(media.getValue());
            m.setRushOrderAvailable(media.isRushOrderAvailable());
            // Update specific fields based on the type of media
            if (media instanceof Book) {
                Book book = (Book) m;
                Book newBook = (Book) media;
                book.setAuthor(newBook.getAuthor());
                book.setCoverType(newBook.getCoverType());
                book.setGenre(newBook.getGenre());
                book.setLanguage(newBook.getLanguage());
                book.setPublisher(newBook.getPublisher());
                book.setNumOfPages(newBook.getNumOfPages());
                book.setPublishDate(newBook.getPublishDate());
                em.persist(book);
            } else if (media instanceof CD) {
                CD cd = (CD) m;
                CD newCD = (CD) media;
                cd.setArtist(newCD.getArtist());
                cd.setMusicType(newCD.getMusicType());
                cd.setRecordLabel(newCD.getRecordLabel());
                cd.setReleasedDate(newCD.getReleasedDate());
                em.persist(cd);
            } else if (media instanceof DVD) {
                DVD dvd = (DVD) m;
                DVD newDVD = (DVD) media;
                dvd.setDirector(newDVD.getDirector());
                dvd.setDiscType(newDVD.getDiscType());
                dvd.setGenre(newDVD.getGenre());
                dvd.setStudio(newDVD.getStudio());
                dvd.setLanguage(newDVD.getLanguage());
                dvd.setRuntime(newDVD.getRuntime());
                dvd.setSubtitles(newDVD.getSubtitles());
                dvd.setReleasedDate(newDVD.getReleasedDate());
                em.persist(dvd);

            }
            // Commit the transaction to save changes
            em.getTransaction().commit();
        } else {
            System.out.println("Media with ID " + media.getId() + " not found.");
            em.getTransaction().rollback();
        }

    }

    @Override
    public List<Media> searchMedia(String keyword) {
        EntityManager em = AIMSDB.getEntityManager();
        List<Media> mediaList = em.createQuery("select m from Media  m where m.title like :keyword")
                .setParameter("keyword", "%" + keyword + "%").getResultList();

        return mediaList;
    }
}
