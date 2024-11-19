package com.capstone.gieokdama.domain.mission;

import jakarta.persistence.*;

@Entity
@Table(name="rec_mission")
public class RecMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String emoji;
    private String description;

    protected RecMission() {}

    public RecMission(String title, String emoji, String description) {
        this.title = title;
        this.emoji = emoji;
        this.description = description;
    }

    public int getId() {
        return id;
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
