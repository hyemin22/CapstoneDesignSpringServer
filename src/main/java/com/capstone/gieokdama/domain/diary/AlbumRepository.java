package com.capstone.gieokdama.domain.diary;

import com.capstone.gieokdama.dto.diary.response.AlbumResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT a FROM Album a WHERE a.familyId = :familyId")
    List<AlbumResponse> findAllByFamilyId(Long familyId);
}
