package com.capstone.gieokdama.service.family;

import com.capstone.gieokdama.domain.family.Family;
import com.capstone.gieokdama.domain.family.FamilyRepository;
import com.capstone.gieokdama.domain.user.User;
import com.capstone.gieokdama.domain.user.UserRepository;
import com.capstone.gieokdama.dto.family.request.FamilyCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    public FamilyService(FamilyRepository familyRepository, UserRepository userRepository) {
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long saveFamily(Long represent_userId) {
        Long familyId = generateUniqueRandomNumber();

        // Family 저장
        Family family = new Family(familyId, represent_userId); // familyId를 전달
        familyRepository.save(family);

        // 유저 family_id 값 수정해주기
        User user = userRepository.findById(represent_userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.updateFamily_id(family.getId());  // family_id 업데이트
        userRepository.save(user);  // 업데이트된 유저 저장

        return family.getId(); // 가족 아이디 반환
    }

    private Long generateUniqueRandomNumber() {
        Long randomId;
        do {
            randomId = generateRandomNumber();
        } while (familyRepository.existsById(randomId)); // 존재 여부 확인
        return randomId;
    }

    private Long generateRandomNumber() {
        // 0-999999 랜덤 숫자 생성
        long randomNumber = (long) (Math.random() * 1000000);
        // 6자리 문자열로 포맷팅
        String formattedNumber = String.format("%06d", randomNumber);
        return Long.valueOf(formattedNumber);
    }
}
