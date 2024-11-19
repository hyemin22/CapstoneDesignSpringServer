package com.capstone.gieokdama.dto.mission.request;

import java.time.LocalTime;

public class MissionCreateRequest {

    private Long id;
    private String title;
    private String emoji;
    private Integer cycle;
    private String repeat_day;
    private Integer repeat_time;
    private boolean alarm;
    private LocalTime alarm_time;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getEmoji() {
        return emoji;
    }

    public Integer getCycle() {
        return cycle;
    }

    public String getRepeat_day() {
        return repeat_day;
    }

    public Integer getRepeat_time() {
        return repeat_time;
    }

    public boolean getAlarm() {
        return alarm;
    }

    public LocalTime getAlarm_time() {
        return alarm_time;
    }
}
