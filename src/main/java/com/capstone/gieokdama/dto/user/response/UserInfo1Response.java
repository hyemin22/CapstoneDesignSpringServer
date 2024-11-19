package com.capstone.gieokdama.dto.user.response;

import com.capstone.gieokdama.domain.user.User;

public class UserInfo1Response {
    private Long id;
    private String nickname;
    private String character_choice;

    public UserInfo1Response(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.character_choice = user.getCharacter_choice();
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCharacter_choice() {
        return character_choice;
    }
}
