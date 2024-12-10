package com.capstone.gieokdama.domain.mission;

import com.capstone.gieokdama.dto.mission.response.MissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m WHERE m.user_id = :userId")
    List<MissionResponse> findAllByUser_id(Long userId);

    @Query("SELECT m FROM Mission m WHERE m.user_id = :userId " +
            "AND (m.goal_time = 7 " +
            "OR (m.repeat_day LIKE '%' || :today || '%' )" +
            "OR (m.cycle IN ('주간 목표', '월간 목표') AND m.now_time < m.goal_time))")
    List<MissionResponse> findAllByConditions(Long userId, String today);

}
