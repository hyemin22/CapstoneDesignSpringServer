package com.capstone.gieokdama.domain.post;

import com.capstone.gieokdama.domain.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @JoinColumn(name = "anonymous_sender")
    private String anonymous_sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    protected Post() {}

    public Post(User sender, String anonymous_sender, User receiver, String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("내용이 입력되지 않았습니다.");
        }
        this.sender = sender;
        this.anonymous_sender = anonymous_sender;
        this.receiver = receiver;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public String getAnonymous_sender() {
        return anonymous_sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreated_at() {
        return createdAt;
    }
}
