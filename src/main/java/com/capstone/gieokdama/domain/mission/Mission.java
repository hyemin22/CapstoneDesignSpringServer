package com.capstone.gieokdama.domain.mission;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String emoji;

    private String cycle;

    private String repeat_day;

    private Integer repeat_time;

    private Integer now_time;

    private Integer goal_time;

    @Column(nullable = false)
    private boolean alarm;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime alarm_time;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    protected Mission() {}

    public Mission(Long user_id, String title, String emoji,
                   String cycle, String repeat_day, Integer repeat_time,
                   Integer now_time, Integer goal_time,
                   boolean alarm, LocalTime alarm_time) {
        if (title == null || title.isBlank() || emoji == null || emoji.isBlank()) {
            throw new IllegalArgumentException("필수값이 입력되지 않았습니다.");
        }
        this.user_id = user_id;
        this.title = title;
        this.emoji = emoji;
        this.cycle = cycle;
        this.repeat_day = repeat_day;
        this.repeat_time = repeat_time;
        this.now_time = now_time;
        this.goal_time = goal_time;
        this.alarm = alarm;
        this.alarm_time = alarm_time;
    }

    public Long getId() {
        return id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getCycle() {
        return cycle;
    }

    public String getRepeat_day() {
        return repeat_day;
    }

    public Integer getRepeat_time() {
        return repeat_time;
    }

    public Integer getNow_time() {
        return now_time;
    }

    public Integer getGoal_time() {
        return (goal_time != null) ? goal_time : 0;
    }

    public boolean getAlarm() {
        return alarm;
    }

    public LocalTime getAlarm_time() {
        return alarm_time;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateEmoji(String emoji) {
        this.emoji = emoji;
    }

    public void updateCycle(String cycle) {
        this.cycle = cycle;
    }

    public void updateRepeatDay(String repeat_day) {
        this.repeat_day = repeat_day;
    }

    public void updateRepeatTime(Integer repeat_time) {
        this.repeat_time = repeat_time;
    }

    public void updateNowTime(Integer now_time) {
        this.now_time = now_time;
    }

    public void updateGoalTime(Integer goal_time) {
        this.goal_time = goal_time;
    }

    public void updateAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public void updateAlarm_time(LocalTime alarm_time) {
        this.alarm_time = alarm_time;
    }
}
