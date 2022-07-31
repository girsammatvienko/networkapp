package com.group.networkapp.utils.mapper.impl;

import com.group.networkapp.domain.entity.Post;
import com.group.networkapp.dto.PostDto;
import com.group.networkapp.utils.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapper extends Mapper<Post, PostDto> {
    private final CommentsMapper commentsMapper;
    @Override
    public PostDto toDto(Post post) {
        return PostDto.builder()
                .postId(post.getId())
                .userId(post.getUser().getId())
                .title(post.getTitle())
                .text(post.getText())
                .comments(post.getComments().stream()
                        .map(commentsMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Post toEntity(PostDto postDto) {
        return null;
    }
}
