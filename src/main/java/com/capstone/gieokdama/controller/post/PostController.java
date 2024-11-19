package com.capstone.gieokdama.controller.post;

import com.capstone.gieokdama.dto.post.request.PostCreateRequest;
import com.capstone.gieokdama.dto.post.response.PostResponse;
import com.capstone.gieokdama.service.post.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public void savePost(@RequestBody PostCreateRequest request) {
        postService.savePost(request);
    }

    @GetMapping("/post")
    public List<PostResponse> getPost(@RequestParam Long receiver_id) {
        return postService.getPostList(receiver_id);
    }
}
