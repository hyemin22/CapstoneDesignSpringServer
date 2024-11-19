package com.capstone.gieokdama.domain.scrap;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UserScrap {

    @EmbeddedId
    private UserScrapId id;

    protected UserScrap() {}

    public UserScrap(UserScrapId id) {
        this.id = id;
    }

    public UserScrapId getId() {
        return id;
    }
}
