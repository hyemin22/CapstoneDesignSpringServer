package com.capstone.gieokdama.domain.guestbook;

import com.capstone.gieokdama.domain.family.Family;
import jakarta.persistence.*;

@Entity
@Table(name = "guestbook")
public class GuestBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false)
    private Long writer;

    @Column(nullable = false)
    private Long family_id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "family_id", insertable = false, updatable = false)
    private Family family;

    protected GuestBook() {}

    public GuestBook(Long writer, Long family_id, String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException(String.format("내용이 입력되지 않았습니다.", content));
        }
        this.writer = writer;
        this.family_id = family_id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Long getWriter() {
        return writer;
    }

    public Long getFamily_id() {
        return family_id;
    }

    public String getContent() {
        return content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public Family getFamily() { // Family 객체 반환
        return family;
    }
}
