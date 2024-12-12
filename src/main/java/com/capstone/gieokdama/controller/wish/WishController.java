package com.capstone.gieokdama.controller.wish;

import com.capstone.gieokdama.dto.wish.request.WishCategoryCreateRequest;
import com.capstone.gieokdama.dto.wish.request.WishCategoryUpdateRequest;
import com.capstone.gieokdama.dto.wish.request.WishCreateRequest;
import com.capstone.gieokdama.dto.wish.request.WishUpdateRequest;
import com.capstone.gieokdama.dto.wish.response.WishCategoryResponse;
import com.capstone.gieokdama.dto.wish.response.WishCompletedResponse;
import com.capstone.gieokdama.dto.wish.response.WishResponse;
import com.capstone.gieokdama.service.wish.WishService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    // 카테고리 추가
    @PostMapping("/wish/category")
    public void saveWishCategory(@RequestBody WishCategoryCreateRequest request) {
        wishService.saveWishCategory(request);
    }

    // 카테고리 조회
    @GetMapping("/wish/category")
    public List<WishCategoryResponse> getWishCategory(@RequestParam Long userId) { //PARAMS 이름이랑 동일해야함
        return wishService.getWishCategory(userId);
    }

    // 카테고리 이름 수정
    @PutMapping("/wish/category")
    public void updateWishCategory(@RequestBody WishCategoryUpdateRequest request) {
        wishService.updateWishCategory(request);
    }

    // 위시 카테고리 삭제
    @DeleteMapping("/wish/category")
    public void deleteWishCategory(@RequestParam Integer id) {
        wishService.deleteWishCategory(id);
    }

    // 위시 추가
    @PostMapping("/wish")
    public void saveWish(@RequestBody WishCreateRequest request) {
        wishService.saveWish(request);
    }

    // 예정된 위시리스트 조회
    @GetMapping("/wish/expected")
    public List<WishResponse> getWishExpected(@RequestParam Long userId,
                                              @RequestParam Integer category) {
        return wishService.getWishExpected(userId, category);
    }

    // 2주 내의 위시리스트 조회
    @GetMapping("/wish/recent")
    public List<WishResponse> getWishRecent(@RequestParam Long userId) {
        return wishService.getWishRecent(userId);
    }

    // 완료된 위시리스트 조회
    @GetMapping("/wish/completed")
    public List<WishCompletedResponse> getWishCompleted(@RequestParam Long userId) {
        return wishService.getWishCompleted(userId);
    }

    // 위시리스트 예정 -> 완료로 상태 바꾸기
    @PutMapping("/wish/state")
    public void updateWishState(@RequestParam Long id) {
        wishService.updateWishState(id);
    }

    // 예정된 위시리스트 전체 개수 조회
    @GetMapping("/wish/num")
    public Integer getWishExpectedNum(@RequestParam Long userId,
                                      @RequestParam boolean completed) {
        return wishService.getWishExpectedNum(userId, completed);
    }

    // 위시 수정
    @PutMapping("/wish")
    public void updateWish(@RequestBody WishUpdateRequest request) {
        wishService.updateWish(request);
    }

    // 위시 삭제
    @DeleteMapping("/wish")
    public void deleteWish(@RequestParam Long id) {
        wishService.deleteWish(id);
    }

    // 위시 좋아요 추가
    @PostMapping("/wish/like")
    public void saveLike(@RequestParam Long wishId,
                         @RequestParam Long userId) {
        wishService.saveLike(wishId, userId);
    }

    // 위시 좋아요 조회
    @GetMapping("/wish/like")
    public List<Long> getLike(@RequestParam Long wishId) {
        return wishService.getLike(wishId);
    }

    // 위시 좋아요 삭제
    @DeleteMapping("/wish/like")
    public void deleteLike(@RequestParam Long wishId,
                           @RequestParam Long userId) {
        wishService.deleteLike(wishId, userId);
    }
}
