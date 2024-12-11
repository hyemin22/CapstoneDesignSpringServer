package com.capstone.gieokdama.dto.wish.response;

import com.capstone.gieokdama.domain.wish.Wish;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class WishCompletedResponse {

    private Long id;
    private String title;
    private String startDate;
    private String endDate;
    private Integer category;
    private String emoji;
    private boolean alarm;
    private String memo;
    private String completedDate;
    private String dday;
    private long diffInDays;
    private Long diaryId;

    public WishCompletedResponse(Wish wish) {
        this.id = wish.getId();
        this.title = wish.getTitle();
        this.startDate = wish.getStart_date();
        this.endDate = wish.getEnd_date();
        this.category = wish.getCategory();
        this.emoji = wish.getEmoji();
        this.alarm = wish.getAlarm();
        this.memo = wish.getMemo();
        this.completedDate = wish.getCompleted_date();
        this.dday = calculateDday(this.startDate);
        this.diaryId = wish.getDiaryId();
    }

    private String calculateDday(String startDate) {
        // 날짜 포맷 설정 및 요일 부분 제외
        String dateWithoutWeekday = startDate.split("\\(")[0].trim(); // 요일 제거
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.KOREAN);
        LocalDate start = LocalDate.parse(dateWithoutWeekday, formatter);
        LocalDate now = LocalDate.now();

        if (startDate != null) {
            // 날짜 차이 계산
            long daysBetween = ChronoUnit.DAYS.between(now, start);

            if (daysBetween > 0) {
                return "D-" + daysBetween; // D-day
            } else if (daysBetween < 0) {
                return "D+" + Math.abs(daysBetween); // 과거
            } else {
                return "D-day"; // 오늘
            }
        } else {
            // 미정
            return "미정";
        }
    }

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

    public boolean getAlarm() {
        return alarm;
    }

    public String getMemo() {
        return memo;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public String getDday() {
        return dday;
    }

    public long getDiffInDays() {
        return diffInDays;
    }

    public void setDiffInDays(long diffInDays) {
        this.diffInDays = diffInDays;
    }

    public Long getDiaryId() {
        return diaryId;
    }
}
