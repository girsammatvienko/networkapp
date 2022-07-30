package com.group.networkapp.controller;

import com.group.networkapp.dto.UserDto;
import com.group.networkapp.security.UserService;
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

    @GetMapping("/get")
    public void post() {
        System.out.println("get");
    }

    @PostMapping("/user")
    public UserDto registerUser(@RequestBody @Valid UserDto userDto) {
        return userService.registerNewUser(userDto);
    }
}
