package com.group.networkapp.dto.response;

import com.group.networkapp.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class CommentResponse {
    @NotEmpty
    private String text;
    private UserDto userDto;
}
