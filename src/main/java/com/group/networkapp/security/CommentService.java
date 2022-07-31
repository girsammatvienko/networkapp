package com.group.networkapp.security;

import com.group.networkapp.dto.CommentDto;
import com.group.networkapp.dto.request.CommentRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    CommentDto leaveComment(CommentRequest commentRequest);
}
