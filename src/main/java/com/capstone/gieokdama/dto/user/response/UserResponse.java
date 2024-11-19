package com.capstone.gieokdama.dto.user.response;

import com.capstone.gieokdama.domain.user.User;

public class UserResponse {
    private String nickname;
    private String character_choice;
    private String phone_number;

    public UserResponse(User user) {
        this.nickname = user.getNickname();
        this.character_choice = user.getCharacter_choice();
        this.phone_number = user.getPhone_number();
    }

    public String getNickname() {
        return nickname;
    }

    public String getCharacter_choice() {
        return character_choice;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
