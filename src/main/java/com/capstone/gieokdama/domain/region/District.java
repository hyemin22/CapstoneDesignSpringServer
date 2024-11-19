package com.capstone.gieokdama.domain.region;

import jakarta.persistence.*;

@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    private String name;

    public int getId() {
        return id;
    }

    public Province getProvince() {
        return province;
    }

    public String getName() {
        return name;
    }
}
