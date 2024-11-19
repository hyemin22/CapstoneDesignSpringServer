package com.capstone.gieokdama.dto.wish.request;

public class WishUpdateRequest {

    private Long id;
    private String title;
    private String startDate;
    private String endDate;
    private Integer category;
    private String emoji;
    private Boolean alarm;
    private String memo;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Integer getCategory() {
        return category;
    }

    public String getEmoji() {
        return emoji;
    }

    public Boolean getAlarm() {
        return alarm;
    }

    public String getMemo() {
        return memo;
    }
}


