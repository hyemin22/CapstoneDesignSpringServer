package com.capstone.gieokdama.dto.diary.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class DiaryCreateRequest {

    private Long id; //post 시에는 유저 아이디, put시에는 일기 아이디
    private String title;
    private String diary_date;
    private String content;
    private String address;
    private Long album_id;
    private List<String> photos; // 사진 URL을 저장할 리스트 추가
    private Double latitude;
    private Double longitude;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDiary_date() {
        return diary_date;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public void addPhoto(String photoUrl) {
        if (this.photos == null) {
            this.photos = new ArrayList<>();
        }
        this.photos.add(photoUrl);
    }

    public String getContent() {
        return content;
    }

    public String getAddress() {
        return address;
    }

    public Long getAlbum_id() {
        return album_id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDiary_date(String diary_date) {
        this.diary_date = diary_date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAlbum_id(Long album_id) {
        this.album_id = album_id;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
