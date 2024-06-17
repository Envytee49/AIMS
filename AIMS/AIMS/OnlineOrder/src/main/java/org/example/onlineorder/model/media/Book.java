package org.example.onlineorder.model.media;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
@Data
@Entity
@Table(name = "books")
public class Book extends Media {
    private String author;
    private String coverType;
    private String publisher;
    private Date publishDate;
    private int numOfPages;
    private String language;
    private String genre;
}
