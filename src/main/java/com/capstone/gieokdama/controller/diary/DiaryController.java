package com.capstone.gieokdama.controller.diary;

import com.capstone.gieokdama.dto.diary.response.*;
import com.capstone.gieokdama.s3.S3Service;
import com.capstone.gieokdama.dto.diary.request.AlbumCreateRequest;
import com.capstone.gieokdama.dto.diary.request.DiaryCreateRequest;
import com.capstone.gieokdama.service.diary.DiaryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class DiaryController {

    private final DiaryService diaryService;
    private final S3Service s3Service;

    public DiaryController(DiaryService diaryService, S3Service s3Service) {
        this.diaryService = diaryService;
        this.s3Service = s3Service;
    }

    // 앨범 추가
    @PostMapping("/album")
    public void saveAlbum(@RequestBody AlbumCreateRequest request) {
        diaryService.saveAlbum(request);
    }

    // 앨범 확인
    @GetMapping("/album")
    public List<AlbumResponse> getAlbum(@RequestParam Long userId) {
        return diaryService.getAlbum(userId);
    }

    // 앨범명 수정
    @PutMapping("/album")
    public void updateAlbumName(@RequestParam Long albumId,
                                @RequestParam String albumName) {
        diaryService.updateAlbumName(albumId, albumName);
    }

    // 앨범 삭제
    @DeleteMapping("/album")
    public void deleteAlbum(@RequestParam Long albumId) {
        diaryService.deleteAlbum(albumId);
    }


    // 일기 추가
    @PostMapping("/diary")
    public void saveDiary(@ModelAttribute DiaryCreateRequest request,
                          @RequestParam List<MultipartFile> files,
                          @RequestParam Optional<Long> wishId) {

        if (files == null || files.isEmpty()) {
            System.out.println("No files provided");
        }

        // 이미지 S3에 업로드
        for (int i = 0; i < files.size(); i++) {
            // S3에 파일 업로드
            String photoUrl = s3Service.uploadFile(files.get(i), "gieokdama-bucket");

            // 각 파일을 photos 리스트에 추가
            request.addPhoto(photoUrl); // photos 리스트에 URL 추가
        }

        diaryService.saveDiary(request, wishId);
    }

    // 일기 확인(앨범별)
    @GetMapping("/diary/album")
    public List<DiaryResponse> getDiary(@RequestParam Long userId,
                                        @RequestParam Long albumId) {
        return diaryService.getDiary(userId, albumId);
    }

    // 일기 확인(날짜별)
    @GetMapping("/diary/date")
    public List<DiaryResponse> getDiary2(@RequestParam Long userId,
                                         @RequestParam String date) {
        return diaryService.getDiary2(userId, date);
    }

    // 일기 개수 확인(날짜별)
    @GetMapping("/diary/num")
    public List<DiaryNum> getDiaryNum(@RequestParam Long userId) {
        return diaryService.getDiaryNum(userId);
    }

    // 일기 전체 개수 확인
    @GetMapping("/diary/all")
    public Integer getAllDiaryNum(@RequestParam Long userId) {
        return diaryService.getAllDiaryNum(userId);
    }

    // 일기 확인(위치별)
    @GetMapping("/diary/address")
    public List<DiaryAddressResponse> getDiary3(@RequestParam Long userId) {
        return diaryService.getDiary3(userId);
    }

    // 일기 확인(개별)
    @GetMapping("/diary")
    public DiaryResponse getDiaryInfo(@RequestParam Long id) {
        return diaryService.getDiaryInfo(id);
    }

    // 일기 삭제
    @DeleteMapping("/diary")
    public void deleteDiary(@RequestParam Long id) {
        diaryService.deleteDiary(id);
    }

    // 공감 추가
    @PostMapping("/diary/like")
    public void saveLike(@RequestParam Long diaryId,
                         @RequestParam Integer type,
                         @RequestParam Long userId) {
        diaryService.saveLike(diaryId, type, userId);
    }

    // 공감 조회
    @GetMapping("/diary/like")
    public List<DiaryLikeResponse> getLike(@RequestParam Long diaryId) {
        return diaryService.getLike(diaryId);
    }

    // 접속한 사용자가 공감을 추가했는지 조회
    @GetMapping("/diary/like/type")
    public Integer getLikeType(@RequestParam Long diaryId,
                            @RequestParam Long userId) {
        return diaryService.getLikeType(diaryId, userId);
    }

    // 공감 삭제
    @DeleteMapping("/diary/like")
    public void deleteLike(@RequestParam Long diaryId,
                           @RequestParam Integer type,
                           @RequestParam Long userId) {
        diaryService.deleteLike(diaryId, type, userId);
    }
}
