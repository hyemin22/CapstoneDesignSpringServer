package com.capstone.gieokdama.dto.guestbook.response;

public class GuestBookResponse {
    private Long id;
    private String content;
    private String character_choice;
    private String nickname;

    public GuestBookResponse(Long id, String content, String character_choice, String nickname) {
        this.id = id;
        this.content = content;
        this.character_choice = character_choice;
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCharacter_choice() {
        return character_choice;
    }

    public String getNickname() {
        return nickname;
    }
}
