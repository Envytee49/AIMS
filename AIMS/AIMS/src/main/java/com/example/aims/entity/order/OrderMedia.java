package com.example.aims.entity.order;

import com.example.aims.entity.media.Media;
import lombok.Data;

import javax.persistence.*;
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
