package com.capstone.gieokdama.dto.mission.response;

import com.capstone.gieokdama.domain.mission.Mission;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MissionResponse {
    private Long id;
    private String title;
    private String emoji;
    private String cycle;
    private String repeat_day;
    private Integer repeat_time;
    private Integer now_time;
    private Integer goal_time;
    private boolean alarm;
    private String alarm_time;

    public MissionResponse(Mission mission) {
        this.id = mission.getId();
        this.title = mission.getTitle();
        this.emoji = mission.getEmoji();
        this.cycle = mission.getCycle();
        this.repeat_day = mission.getRepeat_day();
        this.repeat_time = mission.getRepeat_time();
        this.now_time = mission.getNow_time();
        this.goal_time = mission.getGoal_time();
        this.alarm = mission.getAlarm();
        this.alarm_time = parseAlarmTime(mission.getAlarm_time());
    }

    // LocalTime을 12시간 AM/PM 형식의 String으로 변환하는 메서드
    private String parseAlarmTime(LocalTime alarmTime) {
        if (alarmTime == null) {
            return null;
        }

        // "hh:mm a" 형식으로 LocalTime을 String으로 변환 (AM/PM 대소문자 처리)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a"); // 12시간 형식 (AM/PM)
        String formattedTime = alarmTime.format(formatter);

        // "오전"을 "AM"으로, "오후"를 "PM"으로 바꾸는 코드
        return formattedTime.replace("오전", "AM").replace("오후", "PM");
    }

    public Long getId() {
        return id;
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
        return goal_time;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public String getAlarm_time() {
        return alarm_time;
    }
}


