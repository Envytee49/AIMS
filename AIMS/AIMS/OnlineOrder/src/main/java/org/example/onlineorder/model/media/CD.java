package org.example.onlineorder.model.media;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
@Data
@Entity
@Table(name = "cds")
public class CD extends Media{
    private String artist;
    private String recordLabel;
    private String musicType;
    private Date releasedDate;
}
