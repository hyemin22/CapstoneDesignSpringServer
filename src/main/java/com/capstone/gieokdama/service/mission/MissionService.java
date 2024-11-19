package com.capstone.gieokdama.service.mission;

import com.capstone.gieokdama.domain.mission.Mission;
import com.capstone.gieokdama.domain.mission.MissionRepository;
import com.capstone.gieokdama.domain.mission.RecMissionRepository;
import com.capstone.gieokdama.dto.mission.request.MissionCreateRequest;
import com.capstone.gieokdama.dto.mission.response.MissionResponse;
import com.capstone.gieokdama.dto.mission.response.RecMissionResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final RecMissionRepository recMissionRepository;

    public MissionService(MissionRepository missionRepository, RecMissionRepository recMissionRepository) {
        this.missionRepository = missionRepository;
        this.recMissionRepository = recMissionRepository;
    }

    // 미션 저장
    @Transactional
    public void saveMission(MissionCreateRequest request) {
        // cycle을 String 형으로 바꾸기
        String cycle = cycleToString(request.getCycle());

        // repeat_day가 한 주에 몇번인지 계산
        Integer repeatDayCount = null;
        if (request.getRepeat_day() != null) {
            repeatDayCount = repeatdayToNum(request.getRepeat_day());
        }

        // goal_time은 계산
        Integer goal_time = setGoalTime(request.getCycle(), repeatDayCount, request.getRepeat_time());

        // Mission 객체 만들기 (now_time은 0으로 자동 초기화)
        Mission mission = new Mission(request.getId(), request.getTitle(), request.getEmoji(),
                cycle, request.getRepeat_day(), request.getRepeat_time(),
                0, goal_time, request.getAlarm(), request.getAlarm_time());

        // 저장
        missionRepository.save(mission);
    }

    // 미션 수정
    @Transactional
    public void updateMission(MissionCreateRequest request) {
        // cycle을 String 형으로 바꾸기
        String cycle = cycleToString(request.getCycle());

        // repeat_day가 한 주에 몇번인지 계산
        Integer repeatDayCount = null;
        if (request.getRepeat_day() != null) {
            repeatDayCount = repeatdayToNum(request.getRepeat_day());
        } else if (cycle.equals("일일 목표")) {
            repeatDayCount = 7;
        }

        // goal_time은 계산
        Integer goal_time = setGoalTime(request.getCycle(), repeatDayCount, request.getRepeat_time());

        // 미션 아이디 가져오기
        Long id = request.getId();
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mission not found"));

        mission.updateTitle(request.getTitle());
        mission.updateEmoji(request.getEmoji());
        mission.updateCycle(cycle);
        mission.updateRepeatDay(request.getRepeat_day());
        mission.updateRepeatTime(request.getRepeat_time());
        mission.updateNowTime(mission.getNow_time());
        mission.updateGoalTime(goal_time);
        mission.updateAlarm(request.getAlarm());
        mission.updateAlarm_time(request.getAlarm_time());
    }

    // 미션 조회
    @Transactional(readOnly = true)
    public List<MissionResponse> getMission(Long user_id) {
        return missionRepository.findAllByUser_id(user_id);
    }

    @Transactional(readOnly = true)
    public List<RecMissionResponse> getRecMission(Long user_id) {
        return recMissionRepository.findAllRecMissionByUser_id(user_id);
    }

    // cycle을 문자형으로 변환
    public String cycleToString(Integer cycle) {
        if (cycle != null) {
            if (cycle == 1) {
                return "일일 목표";
            } else if (cycle == 2 || cycle == 4) {
                return "주간 목표";
            } else if (cycle == 3) {
                return "월간 목표";
            } else {
                return "목표 설정 필요";
            }
        } else {
            return "아직 목표가 설정되어 있지 않아요.";
        }
    }

    // repeat_day (반복 요일) 가 한 주에 몇 번 있는지 계산
    public Integer repeatdayToNum(String repeat_day) {
        // 문자열에서 쉼표(,)의 개수를 센다.
        int commaCount = repeat_day.split(",").length - 1;

        // 쉼표 개수에 1을 더하면 숫자 개수를 알 수 있다.
        return commaCount + 1;
    }

    // goal_time 계산
    // cycle이 1이면 7, 2면 time이 goal_time, 3이면 time이 goal_time, 4면 day가 goal_time
    public Integer setGoalTime(Integer cycle, Integer repeat_day, Integer repeat_time) {
        if (cycle == null) {
            return null;
        }
        if (cycle == 1) {
            return 7;
        }
        if (cycle == 2 || cycle == 3) {
            return repeat_time != null ? repeat_time : 0; // repeat_time이 null일 경우 0 반환
        }
        if (cycle == 4) {
            return repeat_day != null ? repeat_day : 0; // repeat_day가 null일 경우 0 반환
        }
        return 0; // 기본값
    }

    // 미션 삭제
    @Transactional
    public void deleteMission(Long id) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        missionRepository.delete(mission);
    }
}
