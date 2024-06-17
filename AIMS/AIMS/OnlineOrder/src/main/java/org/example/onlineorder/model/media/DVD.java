package org.example.onlineorder.model.media;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
@Entity
@Data
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
}
