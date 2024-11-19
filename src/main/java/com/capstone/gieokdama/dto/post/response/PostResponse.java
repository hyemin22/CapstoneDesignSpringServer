package com.capstone.gieokdama.dto.post.response;

import java.time.LocalDateTime;

public class PostResponse {
    private String sender_name;
    private String anonymous_name;
    private String receiver_name;
    private String content;
    private LocalDateTime created_at;

    public PostResponse(String sender_name, String anonymous_name, String receiver_name,
                        String content, LocalDateTime created_at) {
        this.sender_name = sender_name;
        this.anonymous_name = anonymous_name;
        this.receiver_name = receiver_name;
        this.content = content;
        this.created_at = created_at;
    }

    public String getSender_name() {
        return sender_name;
    }

    public String getAnonymous_name() {
        return anonymous_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }
}
