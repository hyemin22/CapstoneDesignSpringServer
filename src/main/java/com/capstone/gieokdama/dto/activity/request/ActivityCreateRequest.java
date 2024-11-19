package com.capstone.gieokdama.dto.activity.request;

public class ActivityCreateRequest {

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
