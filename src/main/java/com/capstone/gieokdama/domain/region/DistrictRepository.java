package com.capstone.gieokdama.domain.region;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findByProvinceId(int provinceId);
}
