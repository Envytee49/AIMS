package org.example.onlineorder.model;

import javax.persistence.*;

import lombok.Data;
import org.example.onlineorder.model.media.Media;

import java.io.Serializable;

@Entity
@Table(name = "order_medias")
@Data
public class OrderMedia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;
    private int quantity;

}
