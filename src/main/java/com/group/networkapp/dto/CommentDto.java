package com.group.networkapp.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class CommentDto {
    @NotEmpty
    private Long postId;
    @NotEmpty
    private String text;
    private UserDto userDto;
}
