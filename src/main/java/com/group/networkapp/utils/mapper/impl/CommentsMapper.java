package com.group.networkapp.utils.mapper.impl;

import com.group.networkapp.domain.entity.Comment;
import com.group.networkapp.dto.CommentDto;
import com.group.networkapp.utils.mapper.Mapper;
import com.group.networkapp.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentsMapper extends Mapper<Comment, CommentDto> {
    private final UserMapper userMapper;

    @Override
    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .postId(comment.getPost().getId())
                .userDto(userMapper.toDto(comment.getUser()))
                .text(comment.getText())
                .build();
    }

    @Override
    public Comment toEntity(CommentDto commentDto) {
        return null;
    }
}
