package com.group.networkapp.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class PostRequest {
    @Size(min=5, message = "Post title should be not least than 5 letters")
    private String title;
    @NotEmpty
    private String text;
}
