package com.capstone.gieokdama.service.activity;

import com.capstone.gieokdama.domain.activity.Activity;
import com.capstone.gieokdama.domain.activity.ActivityRepository;
import com.capstone.gieokdama.dto.activity.request.ActivityCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    // 활동 추가
    @Transactional
    public void saveActivity(ActivityCreateRequest request) {
        // Activity 객체 만들기
        Activity activity = new Activity(request.getCategory(), request.getTitle(),
                request.getProfile(), request.getMain_photo(), request.getType(),
                request.getReview_count(), request.getAddress(), request.getDistrict_id(),
                request.getPhone_number(), request.getOpen_time(), request.getClosed_day());

        activityRepository.save(activity);
    }

    // 활동 조회
    @Transactional
    public List<Activity> getActivity(Integer category) {
        // 카테고리로 활동 찾기
        return activityRepository.findAllByCategory(category);
    }
}
