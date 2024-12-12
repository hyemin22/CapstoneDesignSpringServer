package com.capstone.gieokdama.domain.wish;

import com.capstone.gieokdama.dto.wish.response.WishCompletedResponse;
import com.capstone.gieokdama.dto.wish.response.WishResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query("SELECT new com.capstone.gieokdama.dto.wish.response.WishResponse(w) " +
            "FROM Wish w " +
            "WHERE w.familyId = :familyId " +
            "AND w.category = :category " +
            "AND w.completed = :completed")
    List<WishResponse> findAllWishExpected(@Param("familyId") Long familyId,
                                           @Param("category") Integer category,
                                           @Param("completed") boolean completed);

    @Query("SELECT new com.capstone.gieokdama.dto.wish.response.WishCompletedResponse(w) " +
            "FROM Wish w " +
            "WHERE w.familyId = :familyId " +
            "AND w.completed = :completed")
    List<WishCompletedResponse> findAllWishCompleted(@Param("familyId") Long familyId,
                                                     @Param("completed") boolean completed);

    @Query("SELECT new com.capstone.gieokdama.dto.wish.response.WishResponse(w) " +
            "FROM Wish w " +
            "WHERE w.familyId = :familyId " +
            "AND w.completed = :completed")
    List<WishResponse> findByFamilyIdAndCompleted(@Param("familyId") Long familyId,
                                                  @Param("completed") boolean completed);


    @Query("SELECT new com.capstone.gieokdama.dto.wish.response.WishResponse(w) " +
            "FROM Wish w " +
            "WHERE w.familyId = :familyId " +
            "AND w.completed = :completed")
    List<WishResponse> findAllWishRecent(@Param("familyId") Long familyId,
                                         @Param("completed") boolean completed);

    void deleteByCategory(Integer categoryId);

    Optional<Object> findById(Optional<Long> wishId);
}
