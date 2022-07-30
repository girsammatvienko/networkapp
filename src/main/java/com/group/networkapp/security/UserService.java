package com.group.networkapp.security;

import com.group.networkapp.dto.UserDto;
import com.group.networkapp.dto.request.SignInRequest;
import com.group.networkapp.dto.response.SignInResponse;
import com.group.networkapp.domain.exception.UserAlreadyExistsException;

public interface UserService {
    UserDto registerNewUser(UserDto userDto) throws UserAlreadyExistsException;

    SignInResponse login(SignInRequest request);
}
