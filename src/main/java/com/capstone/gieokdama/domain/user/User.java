package com.capstone.gieokdama.domain.user;

import com.capstone.gieokdama.domain.family.Family;
import jakarta.persistence.*;

@Entity
@Table(name = "`user`")
public class User {

    @Id
    @Column(nullable = false)
    private Long id = null;
    @Column(nullable = false, length = 20) //name varchar(20)
    private String nickname;
    @Column(nullable = false, length = 30)
    private String character_choice;
    @Column(nullable = false)
    private Boolean first_term;
    @Column(nullable = false)
    private Boolean second_term;
    @Column(nullable = false)
    private Boolean third_term;
    @Column(length = 20)
    private String phone_number = null;
    private Long family_id;

    @ManyToOne // n:1 관계
    @JoinColumn(name = "family_id", insertable = false, updatable = false) // DB에서 직접 사용하는 family_id를 참조
    private Family family;

    protected User() {}

    public User(Long id, String nickname, String character_choice,
                Boolean first_term, Boolean second_term, Boolean third_term,
                String phone_number, Long family_id) {
        System.out.println("Received data: id=" + id + ", nickname=" + nickname + ", character_choice=" + character_choice);
        if (id == null || nickname == null || character_choice == null
                || first_term == null || second_term == null || third_term == null) {
            throw new IllegalArgumentException("필수값이 전부 입력되지 않아 유저 생성이 불가합니다.");
        }
        this.id = id;
        this.nickname = nickname;
        this.character_choice = character_choice;
        this.first_term = first_term;
        this.second_term = second_term;
        this.third_term = third_term;
        this.phone_number = phone_number;
        this.family_id = family_id;
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

    public Family getFamily() { // Family 객체 반환
        return family;
    }

    public void updateName(String nickname) {
        this.nickname = nickname;
    }

    public void updateFamily_id(Long family_id) {
        this.family_id = family_id;
    }
}
