package com.group.networkapp.dto;

import com.group.networkapp.dto.response.CommentResponse;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class PostDto {
    @NotEmpty
    private Long postId;
    @NotEmpty
    private Long userId;
    @Size(min=5, message = "Post title should be not least than 5 letters")
    private String title;
    @NotEmpty
    private String text;
    private List<CommentDto> comments;
}
