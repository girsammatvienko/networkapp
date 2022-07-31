package com.group.networkapp.controller;

import com.group.networkapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
private final UserService userService;
    @GetMapping("/get")
    public String get() {
        userService.getCurrentUser();
        return "get";
    }

}
