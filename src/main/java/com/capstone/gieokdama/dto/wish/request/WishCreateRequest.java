package com.capstone.gieokdama.dto.wish.request;

public class WishCreateRequest {

    private Long userId;
    private String title;
    private String startDate;
    private String endDate;
    private Integer category;
    private String emoji;
    private Boolean alarm;
    private String memo;
    private Boolean completed;

    public Long getUserId() {
        return userId;
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

    public Boolean getCompleted() {
        return completed;
    }
}
