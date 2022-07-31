package com.group.networkapp.controller;

import com.group.networkapp.dto.PostDto;
import com.group.networkapp.dto.request.PostRequest;
import com.group.networkapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable Long id) {return postService.getPost(id);}

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPost();
    }

    @PostMapping()
    public PostDto createPost(@RequestBody PostRequest postRequest) {
        return postService.createPost(postRequest);
    }
}
