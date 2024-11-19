package com.capstone.gieokdama.controller.activity;

import com.capstone.gieokdama.domain.activity.Activity;
import com.capstone.gieokdama.dto.activity.request.ActivityCreateRequest;
import com.capstone.gieokdama.service.activity.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/activity")
    public void saveActivity(@RequestBody ActivityCreateRequest request) {
        activityService.saveActivity(request);
    }

    @GetMapping("/activity")
    public List<Activity> getActivity(@RequestParam Integer category) {
        return activityService.getActivity(category);
    }
}
