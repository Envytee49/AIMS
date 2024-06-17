package com.example.aims.entity.media;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
@Data
@Entity
@NoArgsConstructor
@Table(name = "cds")
public class CD extends Media{
    private String artist;
    private String recordLabel;
    private String musicType;
    private Date releasedDate;
    public CD(Media media) {
        super(media);
    }
}
