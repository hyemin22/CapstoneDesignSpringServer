package com.capstone.gieokdama.dto.wish.response;

import com.capstone.gieokdama.domain.wish.WishCategory;

public class WishCategoryResponse {
    private Integer id;
    private String name;

    public WishCategoryResponse(WishCategory wishCategory) {
        this.id = wishCategory.getId();
        this.name = wishCategory.getName();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
