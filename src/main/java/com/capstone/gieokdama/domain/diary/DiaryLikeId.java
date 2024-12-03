package com.capstone.gieokdama.domain.diary;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DiaryLikeId implements Serializable {
    private Long diary_id;
    private Integer type;
    private Long user_id;

    // 기본 생성자
    public DiaryLikeId() {}

    public DiaryLikeId(Long diary_id, Integer type, Long user_id) {
        this.diary_id = diary_id;
        this.type = type;
        this.user_id = user_id;
    }

    //equals and hashcode구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryLikeId that = (DiaryLikeId) o;
        return Objects.equals(diary_id, that.diary_id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(user_id, that.user_id); // 필드 값 비교
    }

    @Override
    public int hashCode() {
        return Objects.hash(diary_id, type, user_id); // 필드 값 조합으로 해시 코드 생성
    }
}
