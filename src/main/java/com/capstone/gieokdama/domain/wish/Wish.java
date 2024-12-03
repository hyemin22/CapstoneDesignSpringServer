package com.capstone.gieokdama.domain.wish;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="wish")
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "family_id", nullable = false)
    private Long familyId;

    @Column(nullable = false)
    private String title;

    private String start_date;

    private String end_date;

    @Column(nullable = false)
    private Integer category;

    @Column(nullable = false)
    private String emoji;

    @Column(nullable = false)
    private boolean alarm;

    private String memo;

    @Column(nullable = false)
    private boolean completed;

    @Column(name = "completed_date")
    private LocalDateTime completed_date;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @Column(name = "diary_id")
    private Long diaryId;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    protected Wish() {}

    public Wish(Long familyId, String title, String start_date, String end_date,
                Integer category, String emoji, boolean alarm, String memo,
                boolean completed) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목이 입력되지 않았습니다.");
        }
        this.familyId = familyId;
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.category = category;
        this.emoji = emoji;
        this.alarm = alarm;
        this.memo = memo;
        this.completed = completed;
    }

    // completed를 업데이트하고 완료 시간을 설정하는 메서드
    public void completeWish() {
        this.completed = true;
        this.completed_date = LocalDateTime.now();
        System.out.println("Completed date set to: " + this.completed_date); // 디버깅 로그 추가
    }

    public Long getId() {
        return id;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public String getTitle() {
        return title;
    }

    public String getStart_date() {
        if (start_date == null) {
            return "미정";
        }
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
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

    public boolean getCompleted() {
        return completed;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public String getCompleted_date() {
        if (completed_date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 완료");
        return completed_date.format(formatter);
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void updateEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void updateCategory(Integer category) {
        this.category = category;
    }

    public void updateEmoji(String emoji) {
        this.emoji = emoji;
    }

    public void updateAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public void updateMemo(String memo) {
        this.memo = memo;
    }
}
