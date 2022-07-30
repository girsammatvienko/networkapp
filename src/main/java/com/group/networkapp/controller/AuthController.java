package com.group.networkapp.controller;

import com.group.networkapp.dto.request.SignInRequest;
import com.group.networkapp.dto.response.SignInResponse;
import com.group.networkapp.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public SignInResponse login(@RequestBody SignInRequest request) {
        System.out.println(request.getEmail() + " " + request.getPassword());
        return userService.login(request);
    }
}
