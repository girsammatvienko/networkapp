package com.group.networkapp.utils.mapper;

import com.group.networkapp.domain.entity.NetworkUser;
import com.group.networkapp.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends Mapper<NetworkUser, UserDto>{


    @Override
    public UserDto toDto(NetworkUser networkUser) {
        return UserDto.builder()
                .firstName(networkUser.getFirstName())
                .lastName(networkUser.getLastName())
                .email(networkUser.getEmail())
                .build();
    }

    @Override
    public NetworkUser toEntity(UserDto userDto) {
        return NetworkUser.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }
}
