package com.capstone.gieokdama.domain.mission;

import com.capstone.gieokdama.dto.mission.response.MissionResponse;
import com.capstone.gieokdama.dto.mission.response.RecMissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m WHERE m.user_id = :userId")
    List<MissionResponse> findAllByUser_id(Long userId);
}
