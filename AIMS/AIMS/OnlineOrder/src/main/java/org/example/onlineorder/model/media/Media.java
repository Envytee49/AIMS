package org.example.onlineorder.model.media;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.onlineorder.model.OrderMedia;

import java.util.List;


@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Media { // hibernate framework
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
    @JsonIgnore
    private List<OrderMedia> lstOrderMedia;
    public Media() {

    }

}
