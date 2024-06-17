package com.example.aims.entity.media;

import com.example.aims.entity.AbstractEntity;
import com.example.aims.entity.order.OrderMedia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Media extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private int id;
    private String title;
    private String category;
    private int value;
    private int price;
    private int quantity;
    private String imageURL;
    private boolean rushOrderAvailable;
    private double weight;
    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMedia> lstOrderMedia;
    public Media(Media media) {
        this.id = media.getId();
        this.title = media.getTitle();
        this.category = media.getCategory();
        this.value = media.getValue();
        this.price = media.getPrice();
        this.quantity = media.getQuantity();
        this.rushOrderAvailable = media.isRushOrderAvailable();
        this.weight = media.getWeight();
        this.lstOrderMedia = media.getLstOrderMedia();
    }
}
