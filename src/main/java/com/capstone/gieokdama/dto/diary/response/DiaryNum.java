package com.capstone.gieokdama.dto.diary.response;

import java.time.LocalDate;

public class DiaryNum {
    private String date;
    private Long count;

    public DiaryNum() {}

    public DiaryNum(String date, Long count) {
        this.date = date;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public Long getCount() {
        if(count >= 2) {
            return 2L;
        }
        return count;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
