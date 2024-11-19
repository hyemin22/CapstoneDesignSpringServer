package com.capstone.gieokdama.controller.family;

import com.capstone.gieokdama.service.family.FamilyService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }
}
