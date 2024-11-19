package com.capstone.gieokdama.controller.scrap;

import com.capstone.gieokdama.dto.scrap.request.UserScrapRequest;
import com.capstone.gieokdama.service.scrap.UserScrapService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserScrapController {

    private final UserScrapService userScrapService;

    public UserScrapController(UserScrapService userScrapService) {
        this.userScrapService = userScrapService;
    }

    @PostMapping("/user-scrap")
    public void saveUserScrap(@RequestBody UserScrapRequest request) {
        userScrapService.saveUserScrap(request);
    }
}
