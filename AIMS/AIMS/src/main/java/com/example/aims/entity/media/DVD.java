package com.example.aims.entity.media;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "dvds")
public class DVD extends Media {
    private String discType;
    private String director;
    private int runtime;
    private String studio;
    private String subtitles;
    private Date releasedDate;
    private String language;
    private String genre;

    public DVD(Media media) {
        super(media);
    }
}
