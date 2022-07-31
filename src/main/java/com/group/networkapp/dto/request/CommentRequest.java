package com.group.networkapp.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class CommentRequest {
    @NotEmpty
    private Long postId;
    @NotEmpty
    private String text;
}
