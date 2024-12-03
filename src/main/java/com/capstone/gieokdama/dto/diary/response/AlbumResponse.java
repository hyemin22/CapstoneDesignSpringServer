package com.capstone.gieokdama.dto.diary.response;

import com.capstone.gieokdama.domain.diary.Album;

public class AlbumResponse {
    private Long id;
    private String title;
    private Integer color;

    public AlbumResponse(Album album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.color = album.getColor();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getColor() {
        return color;
    }

}
