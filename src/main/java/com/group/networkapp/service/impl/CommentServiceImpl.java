package com.group.networkapp.service.impl;

import com.group.networkapp.domain.entity.Comment;
import com.group.networkapp.domain.entity.Post;
import com.group.networkapp.domain.exception.NetworkAppException;
import com.group.networkapp.dto.CommentDto;
import com.group.networkapp.dto.request.CommentRequest;
import com.group.networkapp.repository.CommentRepository;
import com.group.networkapp.repository.PostRepository;
import com.group.networkapp.security.CommentService;
import com.group.networkapp.service.UserService;
import com.group.networkapp.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public CommentDto leaveComment(CommentRequest commentRequest) {
        Post post = postRepository.findById(commentRequest.getPostId())
                .orElseThrow(() -> new NetworkAppException("Post not found", HttpStatus.NOT_FOUND));
        var user = userService.getCurrentUser();
        System.out.println(user.getUsername());

        var savedComment = commentRepository.save(Comment.builder()
                .post(post)
                .user(user)
                .text(commentRequest.getText())
                .build());

        var postComments = post.getComments();
        postComments.add(savedComment);

        post.setComments(postComments);
        return CommentDto.builder()
                .postId(post.getId())
                .userDto(userMapper.toDto(user))
                .text(savedComment.getText())
                .build();
    }
}
