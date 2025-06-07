package com.usermanged.usermanagement.controller;

import com.usermanged.usermanagement.customannotation.RateLimited;
import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

//import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/loginservice")
@RequiredArgsConstructor
public class LoginController {

    //Logger logger = getLogger(LoginController.class);

    @RateLimited(limit = 5, timeWindow = 1000)
    @GetMapping("/userProfile")
    public String welcome() {
        return "Welcome to User Profile";
    }
}
