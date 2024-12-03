package com.capstone.gieokdama.domain.diary;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer color;

    @Column(name = "family_id", nullable = false)
    private Long familyId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    protected Album() {}

    public Album(String title, Integer color, Long familyId) {
        if (title == null || title.isBlank()
                || color == null || familyId == null) {
            throw new NullPointerException("앨범 제목과 컬러가 입력되지 않았습니다.");
        }
        this.title = title;
        this.color = color;
        this.familyId = familyId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getColor() {
        return color;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateColor(Integer color) {
        this.color = color;
    }
}
