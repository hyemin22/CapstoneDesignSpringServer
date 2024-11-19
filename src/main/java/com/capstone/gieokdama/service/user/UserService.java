package com.capstone.gieokdama.service.user;

import com.capstone.gieokdama.domain.user.User;
import com.capstone.gieokdama.domain.user.UserRepository;
import com.capstone.gieokdama.dto.user.request.UserCreateRequest;
import com.capstone.gieokdama.dto.user.request.UserUpdateRequest;
import com.capstone.gieokdama.dto.user.response.UserInfo1Response;
import com.capstone.gieokdama.dto.user.response.UserResponse;
import com.capstone.gieokdama.service.family.FamilyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FamilyService familyService;

    public UserService(UserRepository userRepository, FamilyService familyService) {
        this.userRepository = userRepository;
        this.familyService = familyService;
    }

    // 아래 함수가 시작될 때 start transaction; 을 해줌.
    // 함수가 예외 없이 잘 끝났다면 commit
    // 문제가 있다면 rollback
    @Transactional
    public Long saveUser(UserCreateRequest request) {
        User user = new User(request.getId(), request.getNickname(), request.getCharacter_choice(),
                request.getFirst_term(), request.getSecond_term(), request.getThird_term(),
                request.getPhone_number(), request.getFamily_id());

        // 유저를 저장
        userRepository.save(user);

        // family_id 가 null인 경우 가족 새로 생성
        Long familyId = (request.getFamily_id() == null) ? null : request.getFamily_id();
        if (familyId == null) {
            familyId = familyService.saveFamily(request.getId()); // 가족 저장 호출
        }

        return familyId; // 생성된 사용자 ID 반환
    }

    // 가족 아이디 유효성 조회
    public boolean isFamilyIdValid(Long familyId) {
        return userRepository.existsByFamily_id(familyId);
    }

    @Transactional(readOnly = true)
    public List<UserInfo1Response> getFamilyInfo1(Long userId) { //해당 테이블의 모든 정보 가져오기
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        // 가족 아이디로 나를 제외한 우리 가족의 아이디, 닉네임, 캐릭터 정보 받아오기
        return userRepository.findInfo1ByFamily_id(familyId, userId);
    }

    // 유저 존재 여부 조회
    public boolean isUserExist(Long userId) {
        return userRepository.existsById(userId);
    }

    // 유저 정보 조회
    @Transactional(readOnly = true)
    public UserResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);

        return new UserResponse(user);
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getNickname());
        //userRepository.save(user); //이 문장이 없어도 자동 저장됨 - 영속성 컨텍스트 변경 감지기능 때문
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }
}
