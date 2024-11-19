package com.capstone.gieokdama.controller.mission;

import com.capstone.gieokdama.dto.mission.request.MissionCreateRequest;
import com.capstone.gieokdama.dto.mission.response.MissionResponse;
import com.capstone.gieokdama.dto.mission.response.RecMissionResponse;
import com.capstone.gieokdama.service.mission.MissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping("/mission")
    public void saveMission(@RequestBody MissionCreateRequest request) {
        missionService.saveMission(request);
    }

    @GetMapping("/mission")
    public List<MissionResponse> getMyMission(@RequestParam Long userId) {
        return missionService.getMission(userId);
    }

    @GetMapping("/mission/rec")
    public List<RecMissionResponse> getRecMission(@RequestParam Long userId) {
        return missionService.getRecMission(userId);
    }

    @PutMapping("/mission")
    public void updateMission(@RequestBody MissionCreateRequest request) {
        missionService.updateMission(request);
    }

    @DeleteMapping("/mission")
    public void deleteMission(@RequestParam Long id) {
        missionService.deleteMission(id);
    }
    // 오늘 요일에 해당되는 미션만 가져오는 get
}
