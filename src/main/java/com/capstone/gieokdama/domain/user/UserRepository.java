package com.capstone.gieokdama.domain.user;

import com.capstone.gieokdama.dto.user.response.UserInfo1Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // familyId가 존재하는지 확인
    boolean existsByFamily_id(Long family_id);

    // 유저 아이디, 닉네임, 캐릭터 정보 가져오기
    @Query("SELECT u FROM User u WHERE u.family.id = :familyId AND u.id <> :userId")
    List<UserInfo1Response> findInfo1ByFamily_id(@Param("familyId") Long familyId, @Param("userId") Long userId);

    //long countAge(Integer age);
}
