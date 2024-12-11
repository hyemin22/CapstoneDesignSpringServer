package com.capstone.gieokdama.domain.diary;

import com.capstone.gieokdama.dto.diary.response.DiaryAddressResponse;
import com.capstone.gieokdama.dto.diary.response.DiaryNum;
import com.capstone.gieokdama.dto.diary.response.DiaryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("SELECT new com.capstone.gieokdama.dto.diary.response.DiaryResponse(d, d.album.title, d.writer.character_choice, d.writer.nickname) " +
            "FROM Diary d " +
            "WHERE d.family_id = :familyId " +
            "AND d.album.id = :albumId ")
    List<DiaryResponse> findAllDiaryInAlbum(@Param("familyId") Long familyId,
                                            @Param("albumId") Long albumId);

    @Query("SELECT new com.capstone.gieokdama.dto.diary.response.DiaryResponse(d, d.album.title, d.writer.character_choice, d.writer.nickname) " +
            "FROM Diary d " +
            "WHERE d.family_id = :familyId " +
            "AND d.diary_date = :date ")
    List<DiaryResponse> findAllDiaryInDate(@Param("familyId") Long familyId,
                                           @Param("date") String date);

    @Query("SELECT new com.capstone.gieokdama.dto.diary.response.DiaryAddressResponse(d) " +
            "FROM Diary d " +
            "WHERE d.family_id = :familyId ")
    List<DiaryAddressResponse> findAllDiary(@Param("familyId") Long familyId);


    @Query("SELECT new com.capstone.gieokdama.dto.diary.response.DiaryNum(d.diary_date, COUNT(d)) " +
            "FROM Diary d " +
            "WHERE d.family_id = :familyId " +
            "GROUP BY d.diary_date")
    List<DiaryNum> findDiaryNum(@Param("familyId") Long familyId);

    @Query("SELECT COUNT(d) " +
            "FROM Diary d " +
            "WHERE d.family_id = :familyId")
    Integer findAllDiaryNum(@Param("familyId") Long familyId);

}
