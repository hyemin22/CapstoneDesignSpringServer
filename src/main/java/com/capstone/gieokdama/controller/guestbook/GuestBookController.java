package com.capstone.gieokdama.controller.guestbook;

import com.capstone.gieokdama.dto.guestbook.request.GuestBookCreateRequest;
import com.capstone.gieokdama.dto.guestbook.request.GuestBookUpdateRequest;
import com.capstone.gieokdama.dto.guestbook.response.GuestBookResponse;
import com.capstone.gieokdama.service.guestbook.GuestBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuestBookController {

    private final GuestBookService guestBookService;

    public GuestBookController(GuestBookService guestBookService) {
        this.guestBookService = guestBookService;
    }

    @PostMapping("/guestbook")
    public void saveGuestBook(@RequestBody GuestBookCreateRequest request) {
        guestBookService.saveGuestBook(request);
    }

    //우리 가족의 방명록 모두 조회
    @GetMapping("/guestbook/family")
    public List<GuestBookResponse> getGuestBookList(@RequestParam Long user_id) {
        return guestBookService.getGuestBookList(user_id);
    }

    //방명록 수정
    @PutMapping("/guestbook")
    public void updateGuestBook(@RequestBody GuestBookUpdateRequest request) {
        guestBookService.updateGuestBook(request);
    }

    //방명록 삭제
    @DeleteMapping("/guestbook")
    public void deleteGuestbook(@RequestParam Long id) {
        guestBookService.deleteGuestBook(id);
    }
}
