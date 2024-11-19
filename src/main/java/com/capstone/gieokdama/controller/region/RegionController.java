package com.capstone.gieokdama.controller.region;

import com.capstone.gieokdama.domain.region.District;
import com.capstone.gieokdama.domain.region.DistrictRepository;
import com.capstone.gieokdama.domain.region.Province;
import com.capstone.gieokdama.domain.region.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegionController {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @GetMapping("/provinces")
    public List<Province> getProvinces() {
        return provinceRepository.findAll();
    }

    @GetMapping("/districts/{provinceId}")
    public List<District> getDistrictsByProvince(@PathVariable int provinceId) {
        return districtRepository.findByProvinceId(provinceId);
    }
}
