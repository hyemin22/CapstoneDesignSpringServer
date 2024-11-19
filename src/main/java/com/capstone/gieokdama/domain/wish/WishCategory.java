package com.capstone.gieokdama.domain.wish;

import jakarta.persistence.*;

@Entity
@Table(name="wish_category")
public class WishCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "family_id", nullable = false)
    private Long familyId;

    @Column(nullable = false)
    private String name;

    protected WishCategory() {}

    public WishCategory(Long familyId, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("카테고리 이름이 입력되지 않았습니다.");
        }
        this.familyId = familyId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Long getFamily_id() {
        return familyId;
    }

    public String getName() {
        return name;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
