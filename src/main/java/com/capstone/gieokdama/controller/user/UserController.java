package com.capstone.gieokdama.controller.user;

import com.capstone.gieokdama.dto.user.request.UserCreateRequest;
import com.capstone.gieokdama.dto.user.request.UserUpdateRequest;
import com.capstone.gieokdama.dto.user.response.UserInfo1Response;
import com.capstone.gieokdama.dto.user.response.UserResponse;
import com.capstone.gieokdama.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //사용자 생성
    @PostMapping("/user") // Post /user
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> saveUser(@RequestBody UserCreateRequest request) {
        Long familyId = userService.saveUser(request); // family_id 반환
        return ResponseEntity.status(HttpStatus.OK).body(familyId); // 200 OK와 함께 family_id 반환
    }

    //가족 아이디 유효성 조회
    @GetMapping("/user/familyid")
    public ResponseEntity<Boolean> validateFamilyId(@RequestParam Long familyId) {
        boolean isValid = userService.isFamilyIdValid(familyId);
        return ResponseEntity.ok(isValid);
    }

    //유저 아이디로 사용자 존재 여부 조회
    @GetMapping("/user")
    public ResponseEntity<Boolean> getUser(@RequestParam Long userId) {
        boolean isExist = userService.isUserExist(userId);
        return ResponseEntity.ok(isExist);
    }

    //유저 아이디로 사용자 닉네임, 캐릭터, 번호 조회
    @GetMapping("/user/info")
    public UserResponse getUserInfo(@RequestParam Long userId) {
        return userService.getUserInfo(userId);
    }

    //가족 아이디로 가족들의 아이디, 닉네임, 캐릭터 조회
    @GetMapping("/family/info1")
    public List<UserInfo1Response> getFamilyIdNickNameCharcter(@RequestParam Long userId) {
        return userService.getFamilyInfo1(userId);
    }

    //사용자 업데이트
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    //사용자 삭제
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
    }
}
