package com.capstone.gieokdama.dto.user.request;

public class UserCreateRequest  {

    private Long id;
    private String nickname;
    private String character_choice;
    private Boolean first_term;
    private Boolean second_term;
    private Boolean third_term;
    private String phone_number;
    private Long family_id;

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCharacter_choice() {
        return character_choice;
    }

    public Boolean getFirst_term() {
        return first_term;
    }

    public Boolean getSecond_term() {
        return second_term;
    }

    public Boolean getThird_term() {
        return third_term;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Long getFamily_id() {
        return family_id;
    }
}
