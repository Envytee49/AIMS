package com.example.aims.entity.media;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
@Data
@Entity
@NoArgsConstructor
@Table(name = "books")
public class Book extends Media {
    private String author;
    private String coverType;
    private String publisher;
    private Date publishDate;
    private int numOfPages;
    private String language;
    private String genre;
    public Book(Media media) {
        super(media);
    }
}
