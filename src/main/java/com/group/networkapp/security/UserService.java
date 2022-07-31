package com.group.networkapp.security;

import com.group.networkapp.domain.exception.UserAlreadyExistsException;
import com.group.networkapp.dto.UserDto;
import com.group.networkapp.dto.request.SignInRequest;
import com.group.networkapp.dto.response.SignInResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService {
    UserDto registerNewUser(UserDto userDto) throws UserAlreadyExistsException;

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
