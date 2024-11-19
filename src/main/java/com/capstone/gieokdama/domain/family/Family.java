package com.capstone.gieokdama.domain.family;

import jakarta.persistence.*;

@Entity
public class Family {

    @Id
    private Long id;

    @Column(nullable = false)
    private Long represent_user;

    protected Family() {}

    public Family(Long id, Long represent_user) {
        this.id = id;
        this.represent_user = represent_user;
    }

    public Long getId() {
        return id;
    }

    public Long getRepresent_user() {
        return represent_user;
    }
}
