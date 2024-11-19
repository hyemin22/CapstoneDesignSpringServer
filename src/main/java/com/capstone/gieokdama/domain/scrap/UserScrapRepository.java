package com.capstone.gieokdama.domain.scrap;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScrapRepository extends JpaRepository<UserScrap, UserScrapId> {

    // select * from user_scrap where user_id = ? and original_id = ?
    boolean existsById(UserScrapId id);
}
