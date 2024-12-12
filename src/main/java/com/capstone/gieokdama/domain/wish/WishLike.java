package com.capstone.gieokdama.domain.wish;

import jakarta.persistence.*;

@Entity
@Table(name="wish_like")
public class WishLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wish_id", nullable = false)
    private Long wishId;

    @Column(name= "user_id", nullable = false)
    private Long userId;

    protected WishLike() {}

    public WishLike(Long wishId, Long userId) {
        this.wishId = wishId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getWishId() {
        return wishId;
    }

    public Long getUserId() {
        return userId;
    }
}
