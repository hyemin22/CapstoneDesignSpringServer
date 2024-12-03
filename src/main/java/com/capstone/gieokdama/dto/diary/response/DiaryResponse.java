package com.capstone.gieokdama.dto.diary.response;

import com.capstone.gieokdama.domain.diary.Diary;

public class DiaryResponse{
    private Long id;
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

    private String album_title;

    private String user_character;
    private String user_nickname;

    public DiaryResponse() {}

    public DiaryResponse(Diary diary, String album_title, String user_character, String user_nickname) {
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.diary_date = diary.getDiary_date();
        this.photo1 = diary.getPhoto1();
        this.photo2 = diary.getPhoto2();
        this.photo3 = diary.getPhoto3();
        this.photo4 = diary.getPhoto4();
        this.photo5 = diary.getPhoto5();
        this.photo6 = diary.getPhoto6();
        this.photo7 = diary.getPhoto7();
        this.photo8 = diary.getPhoto8();
        this.photo9 = diary.getPhoto9();
        this.photo10 = diary.getPhoto10();
        this.content = diary.getContent();
        this.address = diary.getAddress();
        this.latitude = diary.getLatitude();
        this.longitude = diary.getLongitude();
        this.album_title = album_title;
        this.user_character = user_character;
        this.user_nickname = user_nickname;
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

    public String getAlbum_title() {
        return album_title;
    }

    public String getUser_character() {
        return user_character;
    }

    public String getUser_nickname() {
        return user_nickname;
    }
}
