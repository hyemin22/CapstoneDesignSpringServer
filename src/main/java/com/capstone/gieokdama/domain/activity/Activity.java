package com.capstone.gieokdama.domain.activity;

import jakarta.persistence.*;

@Entity
@Table(name="activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer category;
    private String title;
    private String profile;
    private String main_photo;
    private String type;
    private String review_count;
    private String address;
    private Integer district_id;
    private String phone_number;
    private String open_time;
    private String closed_day;

    protected Activity() {}

    public Activity(Integer category, String title, String profile,
                    String main_photo, String type, String review_count,
                    String address, Integer district_id, String phone_number,
                    String open_time, String closed_day) {
        this.category = category;
        this.title = title;
        this.profile = profile;
        this.main_photo = main_photo;
        this.type = type;
        this.review_count = review_count;
        this.address = address;
        this.district_id = district_id;
        this.phone_number = phone_number;
        this.open_time = open_time;
        this.closed_day = closed_day;
    }

    public Long getId() {
        return id;
    }

    public Integer getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getProfile() {
        return profile;
    }

    public String getMain_photo() {
        return main_photo;
    }

    public String getType() {
        return type;
    }

    public String getReview_count() {
        return review_count;
    }

    public String getAddress() {
        return address;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getOpen_time() {
        return open_time;
    }

    public String getClosed_day() {
        return closed_day;
    }
}
