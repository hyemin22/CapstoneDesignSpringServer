package com.capstone.gieokdama.domain.wish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WishLikeRepository extends JpaRepository<WishLike, Long> {

    @Query("SELECT wl.userId " +
            "FROM WishLike wl " +
            "WHERE wl.wishId = :wishId")
    List<Long> findLikeByWishId(Long wishId);

    Optional<WishLike> findByUserIdAndWishId(Long userId, Long wishId);
}
