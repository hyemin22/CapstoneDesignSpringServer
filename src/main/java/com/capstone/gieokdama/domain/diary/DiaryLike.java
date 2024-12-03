package com.capstone.gieokdama.domain.diary;

import jakarta.persistence.*;

@Entity
@Table(name="diary_like")
public class DiaryLike {

    @EmbeddedId
    private DiaryLikeId id;


    protected DiaryLike() {}

    public DiaryLike(DiaryLikeId id) {
        this.id = id;
    }

    public DiaryLikeId getId() {
        return id;
    }
}
