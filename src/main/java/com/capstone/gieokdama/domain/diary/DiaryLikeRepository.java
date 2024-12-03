package com.capstone.gieokdama.domain.diary;

import com.capstone.gieokdama.dto.diary.response.DiaryLikeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryLikeRepository extends JpaRepository<DiaryLike, DiaryLikeId> {

    // DiaryLikeId 별 개수를 반환하는 메서드
    @Query("SELECT new com.capstone.gieokdama.dto.diary.response.DiaryLikeResponse(dl.id.type, COUNT(dl.id.diary_id)) " +
            "FROM DiaryLike dl " +
            "WHERE dl.id.diary_id = :diaryId " +
            "GROUP BY dl.id.type " +
            "ORDER BY dl.id.type")
    List<DiaryLikeResponse> findLikeCountsByDiaryId(Long diaryId);

    // 사용자가 추가한 공감 타입을 반환하는 메서드
    @Query("SELECT dl.id.type " +
            "FROM DiaryLike dl " +
            "WHERE dl.id.diary_id = :diaryId " +
            "AND dl.id.user_id = :userId")
    Integer findTypeByDiaryIdAndUserId(Long diaryId, Long userId);
}
