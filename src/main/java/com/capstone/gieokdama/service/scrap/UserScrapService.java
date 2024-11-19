package com.capstone.gieokdama.service.scrap;

import com.capstone.gieokdama.domain.scrap.UserScrap;
import com.capstone.gieokdama.domain.scrap.UserScrapId;
import com.capstone.gieokdama.domain.scrap.UserScrapRepository;
import com.capstone.gieokdama.dto.scrap.request.UserScrapRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserScrapService {

    private final UserScrapRepository userScrapRepository;

    public UserScrapService(UserScrapRepository userScrapRepository) {
        this.userScrapRepository = userScrapRepository;
    }

    @Transactional
    public void saveUserScrap(UserScrapRequest request) {

        // 1. 로그인된 사용자 정보 가져오기 (예: Spring Security 사용 시)
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long user_id = (Long) authentication.getPrincipal();  // 실제로 이 부분은 인증된 사용자 정보에 따라 다름
        Long user_id = 2L; // 임시 값

        // 2. 스크랩 정보 확인해서 스크랩 중인지 확인
        UserScrapId userScrapId = new UserScrapId(user_id, request.getOriginal_id());
        if (userScrapRepository.existsById(userScrapId)) {
            // 3. 확인했는데 스크랩중이라면 예외 발생시키기
            throw new IllegalArgumentException("이미 스크랩된 활동입니다.");
        }

        // 3-1. 스크랩중이 아니라면 스크랩 저장
        userScrapRepository.save(new UserScrap(userScrapId));
    }
}
