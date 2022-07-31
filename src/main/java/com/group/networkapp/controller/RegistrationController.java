package com.group.networkapp.controller;

import com.group.networkapp.dto.UserDto;
import com.group.networkapp.dto.request.UserRequest;
import com.group.networkapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/user")
    public UserDto registerUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.registerNewUser(userRequest);
    }
}
