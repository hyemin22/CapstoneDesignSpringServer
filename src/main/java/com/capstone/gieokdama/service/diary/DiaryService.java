package com.capstone.gieokdama.service.diary;

import com.capstone.gieokdama.domain.diary.*;
import com.capstone.gieokdama.domain.user.User;
import com.capstone.gieokdama.domain.user.UserRepository;
import com.capstone.gieokdama.domain.wish.Wish;
import com.capstone.gieokdama.domain.wish.WishRepository;
import com.capstone.gieokdama.dto.diary.request.AlbumCreateRequest;
import com.capstone.gieokdama.dto.diary.request.DiaryCreateRequest;
import com.capstone.gieokdama.dto.diary.response.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DiaryService {

    private final AlbumRepository albumRepository;
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final DiaryLikeRepository diaryLikeRepository;
    private final WishRepository wishRepository;

    public DiaryService(AlbumRepository albumRepository, DiaryRepository diaryRepository,
                        UserRepository userRepository, DiaryLikeRepository diaryLikeRepository, WishRepository wishRepository) {
        this.albumRepository = albumRepository;
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
        this.diaryLikeRepository = diaryLikeRepository;
        this.wishRepository = wishRepository;
    }

    // 앨범 저장
    @Transactional
    public void saveAlbum(AlbumCreateRequest request) {
        // 작성한 사람 정보 가져오기
        Long userId = request.getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        // Album 객체 만들기
        Album album = new Album(request.getTitle(), request.getColor(), familyId);

        albumRepository.save(album);
    }

    // 앨범 조회
    @Transactional(readOnly = true)
    public List<AlbumResponse> getAlbum(Long userId) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        return albumRepository.findAllByFamilyId(familyId);
    }

    // 일기 저장
    @Transactional
    public void saveDiary(DiaryCreateRequest request, Optional<Long> wishId) {
        // 작성한 사람 정보 가져오기
        Long userId = request.getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        // 사진 URL을 받아와서 photo1~photo10에 할당
        List<String> photos = request.getPhotos(); // DiaryCreateRequest의 photos 리스트

        // Album 객체 가져오기
        Long albumId = request.getAlbum_id();
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found with id: " + albumId));

        // Diary 객체 만들기
        Diary diary = new Diary(
                request.getTitle(),
                request.getDiary_date(),
                getPhotoUrl(photos, 0), getPhotoUrl(photos, 1), getPhotoUrl(photos, 2),
                getPhotoUrl(photos, 3), getPhotoUrl(photos, 4), getPhotoUrl(photos, 5),
                getPhotoUrl(photos, 6), getPhotoUrl(photos, 7), getPhotoUrl(photos, 8),
                getPhotoUrl(photos, 9), request.getContent(), request.getAddress(),
                request.getLatitude(), request.getLongitude(),
                album, user, familyId
        );

        diaryRepository.save(diary);

        // wishId가 null일 경우에만 skip 처리
        wishId.ifPresent(id -> {
            if (id == -1) {
                return; // -1일 경우 아무 작업도 하지 않고 반환
            }
            Wish wish = wishRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Wish not found with id: " + id));
            wish.setDiaryId(diary.getId());
            wishRepository.save(wish);
        });
    }

    // 사진 URL 리스트에서 특정 인덱스의 URL을 반환하는 메서드 (없는 경우 null 반환)
    private String getPhotoUrl(List<String> photos, int index) {
        if (index < photos.size()) {
            return photos.get(index);
        }
        return null; // photo1 ~ photo10까지는 최대 10개이므로, 초과하면 null 처리
    }

    // 일기 리스트 조회(앨범별)
    @Transactional(readOnly = true)
    public List<DiaryResponse> getDiary(Long userId, Long albumId) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        return diaryRepository.findAllDiaryInAlbum(familyId, albumId);
    }

    // 일기 리스트 조회(날짜별)
    @Transactional(readOnly = true)
    public List<DiaryResponse> getDiary2(Long userId, String date) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        return diaryRepository.findAllDiaryInDate(familyId, date);
    }

    // 일기 개수 조회 (날짜별)
    @Transactional(readOnly = true)
    public List<DiaryNum> getDiaryNum(Long userId) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        return diaryRepository.findDiaryNum(familyId);
    }

    // 일기 전체 개수 조회
    @Transactional(readOnly = true)
    public Integer getAllDiaryNum(Long userId) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        return diaryRepository.findAllDiaryNum(familyId);
    }


    // 일기 리스트 조회(장소별)
    @Transactional(readOnly = true)
    public List<DiaryAddressResponse> getDiary3(Long userId) {
        // 작성한 사람 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 작성한 사람의 가족 ID 가져오기
        Long familyId = user.getFamily_id();

        return diaryRepository.findAllDiary(familyId);
    }

    // 일기 개별 조회
    @Transactional(readOnly = true)
    public DiaryResponse getDiaryInfo(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Diary not found with id: " + id));

        // 필요한 추가 정보들을 적절히 가져오는 로직 추가
        String albumTitle = diary.getAlbum().getTitle(); // 앨범 제목
        String userCharacter = diary.getWriter().getCharacter_choice(); // 사용자 캐릭터
        String userNickname = diary.getWriter().getNickname(); // 사용자 닉네임

        return new DiaryResponse(diary, albumTitle, userCharacter, userNickname);
    }

    // 일기 삭제
    @Transactional
    public void deleteDiary(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        diaryRepository.delete(diary);
    }

    // 공감 추가
    @Transactional
    public void saveLike(Long diaryId, Integer type, Long userId) {
        DiaryLikeId diaryLikeId = new DiaryLikeId(diaryId, type, userId);

        DiaryLike diaryLike = new DiaryLike(diaryLikeId);
        diaryLikeRepository.save(diaryLike);
    }

    // 공감 조회
    @Transactional(readOnly = true)
    public List<DiaryLikeResponse> getLike(Long diaryId) {
        return diaryLikeRepository.findLikeCountsByDiaryId(diaryId);
    }

    // 접속한 사용자가 어떤 공감을 추가했는지 조회
    @Transactional(readOnly = true)
    public Integer getLikeType(Long diaryId, Long userId) {
        return diaryLikeRepository.findTypeByDiaryIdAndUserId(diaryId, userId);
    }

    // 공감 삭제
    @Transactional
    public void deleteLike(Long diaryId, Integer type, Long userId) {
        DiaryLikeId diaryLikeId = new DiaryLikeId(diaryId, type, userId);

        DiaryLike diaryLike = diaryLikeRepository.findById(diaryLikeId)
                .orElseThrow(IllegalArgumentException::new);

        diaryLikeRepository.delete(diaryLike);
    }

}
