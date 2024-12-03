package com.capstone.gieokdama.dto.diary.response;

public class DiaryLikeResponse {
    private Integer type;
    private Long count;

    public DiaryLikeResponse(Integer type, Long count) {
        this.type = type;
        this.count = count;
    }

    public Integer getType() {
        return type;
    }

    public Long getCount() {
        return count;
    }
}
