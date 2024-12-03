package com.capstone.gieokdama.service.wish;

import com.capstone.gieokdama.domain.user.User;
import com.capstone.gieokdama.domain.user.UserRepository;
import com.capstone.gieokdama.domain.wish.Wish;
import com.capstone.gieokdama.domain.wish.WishCategory;
import com.capstone.gieokdama.domain.wish.WishCategoryRepository;
import com.capstone.gieokdama.domain.wish.WishRepository;
import com.capstone.gieokdama.dto.wish.request.WishCategoryCreateRequest;
import com.capstone.gieokdama.dto.wish.request.WishCategoryUpdateRequest;
import com.capstone.gieokdama.dto.wish.request.WishCreateRequest;
import com.capstone.gieokdama.dto.wish.request.WishUpdateRequest;
import com.capstone.gieokdama.dto.wish.response.WishCategoryResponse;
import com.capstone.gieokdama.dto.wish.response.WishCompletedResponse;
import com.capstone.gieokdama.dto.wish.response.WishResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class WishService {

    private final WishCategoryRepository wishCategoryRepository;
    private final WishRepository wishRepository;
    private final UserRepository userRepository;

    public WishService(WishRepository wishRepository, UserRepository userRepository,
                       WishCategoryRepository wishCategoryRepository) {
        this.wishCategoryRepository = wishCategoryRepository;
        this.wishRepository = wishRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveWishCategory(WishCategoryCreateRequest request) {
        // 작성한 사람 정보 가져오기
        Long userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        // WishCategory 객체 만들기
        WishCategory wishCategory = new WishCategory(familyId, request.getName());

        wishCategoryRepository.save(wishCategory);
    }

    // 위시리스트 카테고리 조회
    @Transactional(readOnly = true)
    public List<WishCategoryResponse> getWishCategory(Long userId) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        return wishCategoryRepository.findAllByFamilyId(familyId);
    }

    // 위시리스트 카테고리 이름 수정
    @Transactional
    public void updateWishCategory(WishCategoryUpdateRequest request) {
        WishCategory category = wishCategoryRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        category.updateName(request.getName());
    }

    // 위시 카테고리 삭제
    @Transactional
    public void deleteWishCategory(Integer id) {
        WishCategory wishCategory = wishCategoryRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        wishCategoryRepository.delete(wishCategory);
    }

    // 위시리스트 저장
    @Transactional
    public void saveWish(WishCreateRequest request) {
        // 작성한 사람 정보 가져오기
        Long userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        // Wish 객체 만들기
        Wish wish = new Wish(familyId, request.getTitle(), request.getStartDate(), request.getEndDate(),
                request.getCategory(), request.getEmoji(), request.getAlarm(), request.getMemo(),
                request.getCompleted());

        wishRepository.save(wish);
    }

    // 예정된 위시 get
    @Transactional(readOnly = true)
    public List<WishResponse> getWishExpected(Long userId, Integer category) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        return wishRepository.findAllWishExpected(familyId, category, false);
    }

    // 2주 내의 예정된 위시 get
    @Transactional(readOnly = true)
    public List<WishResponse> getWishRecent(Long userId) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        List<WishResponse> wishes = wishRepository.findAllWishRecent(familyId, false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        Date currentDate = new Date();
        List<WishResponse> filteredWishes = new ArrayList<>(); // 필터링된 리스트

        for (WishResponse wish : wishes) {
            // startDate 값에서 "(월)"을 제거하고 날짜 문자열만 남깁니다.
            String startDateString = wish.getStartDate().split("\\(")[0].trim(); // "2024.09.10"

            try {
                // 날짜 파싱
                Date startDate = sdf.parse(startDateString);
                long diffInDays = (startDate.getTime() - currentDate.getTime()) / (1000 * 60 * 60 * 24); // 날짜 차이 계산

                // 날짜 차이가 0일에서 14일 사이일 때만 리스트에 추가
                if (diffInDays >= 0 && diffInDays <= 14) {
                    wish.setDiffInDays(diffInDays);
                    filteredWishes.add(wish);
                }
            } catch (Exception e) {
                // 예외가 발생할 경우 예외 처리 (예: 날짜 파싱 오류)
                System.err.println("Error parsing startDate: " + wish.getStartDate());
            }
        }

        // diffInDays 값에 따라 오름차순 정렬
        filteredWishes.sort(Comparator.comparingLong(WishResponse::getDiffInDays));

        return filteredWishes; // 필터링된 목록 반환
    }

    // 완료된 위시 get
    @Transactional(readOnly = true)
    public List<WishCompletedResponse> getWishCompleted(Long userId) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        return wishRepository.findAllWishCompleted(familyId,true);
    }

    // 위시 상태 수정 (예정 -> 완료)
    @Transactional
    public void updateWishState(Long id) {
        Wish wish  = wishRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        wish.completeWish();
        wishRepository.save(wish);
    }

    // 예정된 위시 개수 get
    @Transactional(readOnly = true)
    public Integer getWishExpectedNum(Long userId, boolean completed) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        System.out.println("count" + wishRepository.findByFamilyIdAndCompleted(familyId, false).size());
        return wishRepository.findByFamilyIdAndCompleted(familyId, completed).size();
    }

    // 위시 수정
    @Transactional
    public void updateWish(WishUpdateRequest request) {
        // 위시 아이디 가져오기
        Long id = request.getId();
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        wish.updateTitle(request.getTitle());
        wish.updateStart_date(request.getStartDate());
        wish.updateEnd_date(request.getEndDate());
        wish.updateCategory(request.getCategory());
        wish.updateEmoji(request.getEmoji());
        wish.updateAlarm(request.getAlarm());
        wish.updateMemo(request.getMemo());

        wishRepository.save(wish);
    }

    // 위시 삭제
    @Transactional
    public void deleteWish(Long id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        wishRepository.delete(wish);
    }
}
