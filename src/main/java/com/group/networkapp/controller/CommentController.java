package com.group.networkapp.controller;

import com.group.networkapp.dto.CommentDto;
import com.group.networkapp.dto.request.CommentRequest;
import com.group.networkapp.dto.response.CommentResponse;
import com.group.networkapp.security.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentDto leaveComment(@RequestBody CommentRequest commentRequest) {
        return commentService.leaveComment(commentRequest);
    }
}
