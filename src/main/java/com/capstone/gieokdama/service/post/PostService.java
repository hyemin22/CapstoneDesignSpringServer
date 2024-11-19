package com.capstone.gieokdama.service.post;

import com.capstone.gieokdama.domain.post.Post;
import com.capstone.gieokdama.domain.post.PostRepository;
import com.capstone.gieokdama.domain.user.User;
import com.capstone.gieokdama.domain.user.UserRepository;
import com.capstone.gieokdama.dto.post.request.PostCreateRequest;
import com.capstone.gieokdama.dto.post.response.PostResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void savePost(PostCreateRequest request) {
        // 사용자(보내는 사람) 정보 가져오기
        Long senderId = request.getSenderId();
        // 받는 사람 정보 가져오기
        Long receiverId = request.getReceiverId();

        System.out.println("" + senderId + receiverId);

        // User 객체 가져오기
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        // 저장
        postRepository.save(new Post(sender, request.getAnonymousSender(), receiver, request.getContent()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostList(Long receiver_id) {
        // receiverId로 모든 Post 객체를 가져오기
        List<Post> posts = postRepository.findByReceiver_id(receiver_id);

        return posts.stream()
                .map(post -> {
                    // 보낸 사람 정보 가져오기
                    User sender = userRepository.findById(post.getSender().getId())
                            .orElseThrow(() -> new RuntimeException("Sender not found"));
                    // 받는 사람 정보 가져오기
                    User receiver = userRepository.findById(post.getReceiver().getId())
                            .orElseThrow(() -> new RuntimeException("Receiver not found"));

//                    // 작성 시간 포맷팅
//                    String formattedTimestamp = formatTimestamp(post.getCreated_at());

                    // PostResponse 객체 생성 및 반환
                    return new PostResponse(
                            sender.getNickname(),  // 보낸 사람 닉네임
                            post.getAnonymous_sender(), // 익명 닉네임
                            receiver.getNickname(), // 받는 사람 닉네임
                            post.getContent(),      // 내용
                            post.getCreated_at()     // 작성 시간
                    );
                })
                .collect(Collectors.toList());
    }

//    // 작성 시간을 포맷하는 메서드
//    public String formatTimestamp(LocalDateTime timestamp) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 H시 m분");
//        return timestamp.format(formatter);
//    }
}
