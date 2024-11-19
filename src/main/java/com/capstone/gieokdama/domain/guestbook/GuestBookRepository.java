package com.capstone.gieokdama.domain.guestbook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {

    // familyId로 방명록 찾기
    List<GuestBook> findByFamily_id(Long family_id);

}
