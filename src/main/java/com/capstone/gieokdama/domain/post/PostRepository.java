package com.capstone.gieokdama.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // receiverId로 post 객체 가져오기
    List<Post> findByReceiver_id(Long receiver_id);
}
