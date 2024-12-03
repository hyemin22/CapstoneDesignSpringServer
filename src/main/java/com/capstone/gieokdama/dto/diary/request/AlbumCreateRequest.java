package com.capstone.gieokdama.dto.diary.request;

public class AlbumCreateRequest {

    private Long id; //post 시에는 유저 아이디, put 시에는 앨범 아이디
    private String title;
    private Integer color;

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
