package com.lotto.user.controller;

import com.lotto.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/test")
    public String test() {
        userService.testService();
        return "index2";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
