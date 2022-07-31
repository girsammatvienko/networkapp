package com.group.networkapp.service;

import com.group.networkapp.domain.entity.NetworkUser;
import com.group.networkapp.domain.entity.Post;
import com.group.networkapp.domain.exception.PostNotFoundException;
import com.group.networkapp.dto.PostDto;
import com.group.networkapp.dto.request.PostRequest;
import com.group.networkapp.repository.PostRepository;
import com.group.networkapp.utils.mapper.impl.CommentsMapper;
import com.group.networkapp.utils.mapper.impl.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final CommentsMapper commentsMapper;
    private final PostMapper postMapper;

    public PostDto getPost(Long id) {
        return postMapper.toDto(postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found", HttpStatus.NOT_FOUND)));
    }

    public List<PostDto> getAllPost() {
        return postRepository.findAll().stream()
                .map(post -> PostDto.builder()
                        .postId(post.getId())
                        .userId(post.getUser().getId())
                        .title(post.getTitle())
                        .text(post.getText())
                        .comments(post.getComments().stream()
                                .map(commentsMapper::toDto)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    public PostDto createPost(PostRequest post) {
        NetworkUser currentUser = userService.getCurrentUser();

        var postEntity = Post.builder()
                .title(post.getTitle())
                .text(post.getText())
                .user(currentUser)
                .comments(Collections.emptyList())
                .build();

        var savedPost = postRepository.save(postEntity);
        return PostDto.builder()
                .postId(savedPost.getId())
                .userId(currentUser.getId())
                .title(savedPost.getTitle())
                .text(savedPost.getText())
                .comments(savedPost.getComments().stream()
                        .map(commentsMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
