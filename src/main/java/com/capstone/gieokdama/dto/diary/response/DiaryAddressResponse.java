package com.capstone.gieokdama.dto.diary.response;

import com.capstone.gieokdama.domain.diary.Diary;

public class DiaryAddressResponse {
    Long id;
    Double latitude;
    Double longitude;
    String photo1;

    public DiaryAddressResponse() {}

    public DiaryAddressResponse(Diary diary) {
        this.id = diary.getId();
        this.latitude = diary.getLatitude();
        this.longitude = diary.getLongitude();
        this.photo1 = diary.getPhoto1();
    }

    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getPhoto1() {
        return photo1;
    }
}
