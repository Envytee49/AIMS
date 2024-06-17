package com.example.aims.controller;

import com.example.aims.entity.media.Media;
import com.example.aims.exception.media.MediaInfoException;
import com.example.aims.exception.user.login.EmptyInputException;
import com.example.aims.repository.productmanager.PMRepository;
import com.example.aims.repository.productmanager.PMRepositoryImpl;

import javax.persistence.RollbackException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;

public class CRUDMediaController {
    private PMRepository repository;

    public CRUDMediaController() {
        this.repository = new PMRepositoryImpl();
    }

    // check not empty

    public void addMedia(Media media) {

        repository.addMedia(media);

    }
    public void deleteMedia(Media media) throws RollbackException {
        repository.removeMedia(media.getId());
    }

    public void updateMedia(Media media) {
        repository.updateMedia(media);
    }

    public List<Media> getAllMedia() {
        return repository.getMedias();
    }

    public List<Media> searchMedia(String text) throws EmptyInputException {
        if(text.isBlank()) throw new EmptyInputException("Please fill in the search box");
        return repository.searchMedia(text);
    }
}
