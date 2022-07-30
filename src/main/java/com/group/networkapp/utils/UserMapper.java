package com.group.networkapp.utils;

import com.group.networkapp.domain.entity.NetworkUser;
import com.group.networkapp.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto entityToDto(NetworkUser networkUser) {
        return UserDto.builder()
                .firstName(networkUser.getFirstName())
                .lastName(networkUser.getLastName())
                .email(networkUser.getEmail())
                .password(networkUser.getPassword())
                .build();
    }
}
