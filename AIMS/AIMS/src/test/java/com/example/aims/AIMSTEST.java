package com.example.aims;

import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.order.Invoice;
import com.example.aims.entity.order.Order;
import com.example.aims.entity.order.OrderMedia;
import com.example.aims.entity.delivery.DeliveryInfo;
import com.example.aims.entity.delivery.RushDeliveryInfo;
import com.example.aims.entity.media.Media;
import com.example.aims.repository.AIMSDB;
import com.example.aims.utils.PathConfig;
import com.example.aims.view.payment.InvoiceScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AIMSTEST {
    EntityManager entityManager;

    @Test
    public void createTables() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("aims");
    }

    @BeforeEach
    void setUp() {
        entityManager = AIMSDB.getEntityManager();
    }

    @Test
    public void saveOrderTest() {
        entityManager.getTransaction().begin();

// Create and persist DeliveryInfo
        RushDeliveryInfo deliveryInfo = new RushDeliveryInfo();
        deliveryInfo.setAddress("458 Minh Khai");
        deliveryInfo.setName("Thuan Nguyen");
        deliveryInfo.setEmail("thuannguyen@gmail.com");
        deliveryInfo.setProvince("Ha Noi");
        deliveryInfo.setPhone("0344444128");
        deliveryInfo.setDeliveryInstruction("be fast");
        deliveryInfo.setDeliveryTime("8 AM");
        DeliveryInfo deliveryInfo1 = deliveryInfo;
// Create CartMedia and associate it with Media
        List<CartMedia> cartMediaList = new ArrayList<>();
        Media media1 = entityManager.find(Media.class, 1);
        Media media2 = entityManager.find(Media.class, 2);
        Media media3 = entityManager.find(Media.class, 3);
        CartMedia cartMedia1 = new CartMedia(media1, 1, media1.getPrice());
        CartMedia cartMedia2 = new CartMedia(media2, 2, media2.getPrice());
        CartMedia cartMedia3 = new CartMedia(media3, 3, media3.getPrice());
        cartMediaList.add(cartMedia1);
        cartMediaList.add(cartMedia2);
        cartMediaList.add(cartMedia3);
// Set the list of OrderMedia and other properties

// Persist the Order
        Order order = new Order(cartMediaList, 10000, deliveryInfo1);
        entityManager.persist(order);
        System.out.println(order.getId());
        System.out.println(entityManager.find(Order.class, order.getId()).getDeliveryInfo().getName());

// Commit the transaction
        entityManager.getTransaction().commit();

    }

    @Test
    void removeOrderTest() {
        entityManager.getTransaction().begin();
        Order order = entityManager.find(Order.class, 1);
//        for(OrderMedia orderMedia : order.getLstOrderMedia()) {
//            entityManager.remove(orderMedia);
//        }
//        Order order2 = entityManager.find(Order.class, 1);
//        System.out.println("size " + order2.getLstOrderMedia().size());
        entityManager.remove(order);
        entityManager.getTransaction().commit();
    }
    @Test
    void removeOrderMediaTest() {
        OrderMedia orderMedia = entityManager.find(OrderMedia.class, 2);
        entityManager.getTransaction().begin();
        entityManager.remove(orderMedia);
        entityManager.getTransaction().commit();
    }
    @Test
    void removeMediaTest() {
        entityManager.getTransaction().begin();
        Media media = entityManager.find(Media.class, 1);
        entityManager.remove(media);
        entityManager.getTransaction().commit();
    }
    @Test
    void payOrderTest() throws IOException {
        RushDeliveryInfo deliveryInfo = new RushDeliveryInfo();
        deliveryInfo.setAddress("458 Minh Khai");
        deliveryInfo.setName("Thuan Nguyen");
        deliveryInfo.setEmail("thuannguyen@gmail.com");
        deliveryInfo.setProvince("Ha Noi");
        deliveryInfo.setPhone("0344444128");
        deliveryInfo.setDeliveryInstruction("be fast");
        deliveryInfo.setDeliveryTime("8 AM");
        DeliveryInfo deliveryInfo1 = deliveryInfo;
// Create CartMedia and associate it with Media
        List<CartMedia> cartMediaList = new ArrayList<>();
        Media media1 = entityManager.find(Media.class, 1);
        Media media2 = entityManager.find(Media.class, 2);
        Media media3 = entityManager.find(Media.class, 3);
        CartMedia cartMedia1 = new CartMedia(media1, 1, media1.getPrice());
        CartMedia cartMedia2 = new CartMedia(media2, 2, media2.getPrice());
        CartMedia cartMedia3 = new CartMedia(media3, 3, media3.getPrice());
        cartMediaList.add(cartMedia1);
        cartMediaList.add(cartMedia2);
        cartMediaList.add(cartMedia3);
// Set the list of OrderMedia and other properties

// Persist the Order
        Order order = new Order(cartMediaList, 10000, deliveryInfo1);
        Invoice invoice = new Invoice(order);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PathConfig.INVOICE_SCREEN_PATH));
        Parent root = loader.load();
        InvoiceScreenController baseScreenController = loader.getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        baseScreenController.setCurrentScene(scene);
        baseScreenController.initData(invoice);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }
    @Test
    void testPath() throws FileNotFoundException {
        File initialFile = new File("src/main/resources/com/example/aims/assets/cd/q.jpg");
        System.out.println(initialFile.getAbsolutePath());
        InputStream targetStream = new FileInputStream(initialFile);
    }
}

