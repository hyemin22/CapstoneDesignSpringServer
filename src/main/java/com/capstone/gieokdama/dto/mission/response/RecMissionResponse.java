package com.capstone.gieokdama.dto.mission.response;

public class RecMissionResponse {
    private String title;
    private String emoji;
    private String description;

    public RecMissionResponse(String title, String emoji, String description) {
        this.title = title;
        this.emoji = emoji;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getDescription() {
        return description;
    }
}
