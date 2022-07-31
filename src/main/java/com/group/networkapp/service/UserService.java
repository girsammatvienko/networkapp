package com.group.networkapp.service;

import com.group.networkapp.domain.entity.NetworkUser;
import com.group.networkapp.domain.exception.UserAlreadyExistsException;
import com.group.networkapp.dto.UserDto;
import com.group.networkapp.dto.request.SignInRequest;
import com.group.networkapp.dto.request.UserRequest;
import com.group.networkapp.dto.response.SignInResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserRequest userRequest) throws UserAlreadyExistsException;

    List<UserDto> getAllUsers();

    NetworkUser getCurrentUser();

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
