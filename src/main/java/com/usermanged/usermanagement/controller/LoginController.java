package com.usermanged.usermanagement.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/loginservice")
@RequiredArgsConstructor
public class LoginController {

    //Logger logger = getLogger(LoginController.class);

    @GetMapping("/userProfile")
    public String welcome() {
        return "Welcome to User Profile";
    }
}
