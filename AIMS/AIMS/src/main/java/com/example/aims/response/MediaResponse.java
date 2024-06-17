package com.example.aims.response;

import com.example.aims.entity.media.Media;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MediaResponse {
    private List<Media> mediaList;
    private int mediaCount;
}
