package com.capstone.gieokdama.dto.post.request;

public class PostCreateRequest {

    private Long senderId;

    private String anonymousSender;

    private Long receiverId;

    private String content;

    public Long getSenderId() {
        return senderId;
    }

    public String getAnonymousSender() {
        return anonymousSender;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }
}
