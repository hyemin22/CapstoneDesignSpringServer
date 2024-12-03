package com.capstone.gieokdama.domain.diary;

import com.capstone.gieokdama.domain.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String diary_date;

    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String photo5;
    private String photo6;
    private String photo7;
    private String photo8;
    private String photo9;
    private String photo10;

    private String content;

    private String address;

    private Double latitude;
    private Double longitude;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name="album_id", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "writer", nullable = false)
    private User writer;

    @Column(nullable = false)
    private Long family_id;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    protected Diary() {}

    public Diary(String title, String diary_date,
                 String photo1, String photo2, String photo3,
                 String photo4, String photo5,
                 String photo6, String photo7, String photo8,
                 String photo9, String photo10,
                 String content, String address,
                 Double latitude, Double longitude,
                 Album album, User writer, Long family_id) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목이 입력되지 않았습니다.");
        }
        this.title = title;
        this.diary_date = diary_date;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.photo5 = photo5;
        this.photo6 = photo6;
        this.photo7 = photo7;
        this.photo8 = photo8;
        this.photo9 = photo9;
        this.photo10 = photo10;
        this.content = content;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.album = album; // Album 객체를 그대로 할당
        this.writer = writer; // User 객체를 그대로 할당
        this.family_id = family_id;
    }

    public Album getAlbum() {
        return album;
    }

    public User getWriter() {
        return writer;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDiary_date() {
        return diary_date;
    }

    public String getPhoto1() {
        return photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public String getPhoto5() {
        return photo5;
    }

    public String getPhoto6() {
        return photo6;
    }

    public String getPhoto7() {
        return photo7;
    }

    public String getPhoto8() {
        return photo8;
    }

    public String getPhoto9() {
        return photo9;
    }

    public String getPhoto10() {
        return photo10;
    }

    public String getContent() {
        return content;
    }

    public String getAddress() {
        return address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public Long getFamily_id() {
        return family_id;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateDiary_date(String diary_date) {
        this.diary_date = diary_date;
    }

    public void updatePhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public void updatePhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public void updatePhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public void updatePhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public void updatePhoto5(String photo5) {
        this.photo5 = photo5;
    }

    public void updatePhoto6(String photo6) {
        this.photo6 = photo6;
    }

    public void updatePhoto7(String photo7) {
        this.photo7 = photo7;
    }

    public void updatePhoto8(String photo8) {
        this.photo8 = photo8;
    }

    public void updatePhoto9(String photo9) {
        this.photo9 = photo9;
    }

    public void updatePhoto10(String photo10) {
        this.photo10 = photo10;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateAlbum(Album album) {
        this.album = album;
    }

    public void updateLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void updateLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
