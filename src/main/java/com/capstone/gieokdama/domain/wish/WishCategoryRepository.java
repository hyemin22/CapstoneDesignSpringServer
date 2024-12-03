package com.capstone.gieokdama.domain.wish;

import com.capstone.gieokdama.dto.wish.response.WishCategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishCategoryRepository extends JpaRepository<WishCategory, Integer> {

    @Query("SELECT wc FROM WishCategory wc WHERE wc.familyId = :familyId")
    List<WishCategoryResponse> findAllByFamilyId(Long familyId);
}
