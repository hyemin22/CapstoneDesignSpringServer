package com.capstone.gieokdama.domain.mission;

import com.capstone.gieokdama.dto.mission.response.RecMissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecMissionRepository extends JpaRepository<RecMission, Long> {

    @Query("SELECT new com.capstone.gieokdama.dto.mission.response.RecMissionResponse(r.emoji, r.title, r.description) " +
            "FROM RecMission r LEFT JOIN Mission m " +
            "ON r.title = m.title AND m.user_id = :userId " +
            "WHERE m.id IS NULL")
    List<RecMissionResponse> findAllRecMissionByUser_id(Long userId);
}
