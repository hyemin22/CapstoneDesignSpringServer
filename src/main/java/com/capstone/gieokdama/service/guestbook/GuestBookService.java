package com.capstone.gieokdama.service.guestbook;

import com.capstone.gieokdama.domain.guestbook.GuestBook;
import com.capstone.gieokdama.domain.guestbook.GuestBookRepository;
import com.capstone.gieokdama.domain.user.User;
import com.capstone.gieokdama.domain.user.UserRepository;
import com.capstone.gieokdama.dto.guestbook.request.GuestBookCreateRequest;
import com.capstone.gieokdama.dto.guestbook.request.GuestBookUpdateRequest;
import com.capstone.gieokdama.dto.guestbook.response.GuestBookResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;

    public GuestBookService(GuestBookRepository guestBookRepository, UserRepository userRepository) {
        this.guestBookRepository = guestBookRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveGuestBook(GuestBookCreateRequest request) {

        // 사용자 정보 가져오기
        Long writerId = request.getId();

        // 사용자 정보로부터 familyId 가져오기
        Long familyId = getFamilyIdFromUser(writerId);

        // 저장
        guestBookRepository.save(new GuestBook(writerId, familyId, request.getContent()));
    }

    // 사용자로부터 family_id를 가져오는 메서드
    private Long getFamilyIdFromUser(Long writerId) {
        // writerId로 사용자 조회
        User user = userRepository.findById(writerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFamily_id(); // User 클래스에서 familyId 가져오기
    }

    @Transactional(readOnly = true)
    public List<GuestBookResponse> getGuestBookList(Long user_id) {
        // 유저의 가족 ID를 조회
        Long familyId = getFamilyIdFromUser(user_id);
        if (familyId == null) {
            throw new RuntimeException("Family not found for user");
        }

        // 가족 ID를 사용하여 방명록을 조회
        return guestBookRepository.findByFamily_id(familyId).stream()
                .map(guestBook -> {
                    User user = userRepository.findById(guestBook.getWriter())
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    return new GuestBookResponse(
                            guestBook.getId(),
                            guestBook.getContent(),
                            user.getCharacter_choice(),
                            user.getNickname()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateGuestBook(GuestBookUpdateRequest request) {

        // 클라이언트에서 방명록 id 수집
        Long id = request.getId(); // 바로 findById에 넣어주기

        // 방명록 아이디로 찾기
        GuestBook guestBook = guestBookRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        // 방명록 내용 업데이트
        guestBook.updateContent(request.getContent()); // 자동 변경 감지
    }

    @Transactional
    public void deleteGuestBook(Long id) {
        GuestBook guestBook = guestBookRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        guestBookRepository.delete(guestBook);
    }
}
