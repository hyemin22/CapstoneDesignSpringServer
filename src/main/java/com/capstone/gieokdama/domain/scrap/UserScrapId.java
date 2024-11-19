package com.capstone.gieokdama.domain.scrap;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserScrapId implements Serializable {
    private Long user_id;
    private Long original_id;

    // 기본 생성자
    public UserScrapId() {}

    public UserScrapId(Long user_id, Long original_id) {
        this.user_id = user_id;
        this.original_id = original_id;
    }

    // equals and hashCode 구현
    // 객체의 정확한 비교와 검색을 위해 반드시 구현
    @Override
    public boolean equals(Object o) { // 두 객체가 동일한지 비교
        if (this == o) return true; // 동일하면 true 반환
        if (o == null || getClass() != o.getClass()) return false; // 클래스 유형이 다르면 false
        UserScrapId that = (UserScrapId) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(original_id, that.original_id); // 필드 값 비교
    }

    @Override
    public int hashCode() { // 객체를 해시 기반 컬렉션에 넣을 때 사용하는 해시 값 반환
        return Objects.hash(user_id, original_id); // 필드 값 조합으로 해시 코드 생성
    }
}
