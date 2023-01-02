package com.example.alba_prokect.service;

import com.example.alba_prokect.dto.CommentRequestDto;
import com.example.alba_prokect.dto.CommentResponseDto;
import com.example.alba_prokect.entity.Comment;
import com.example.alba_prokect.entity.Post;
import com.example.alba_prokect.entity.User;
import com.example.alba_prokect.errorcode.CommonStatusCode;
import com.example.alba_prokect.exception.RestApiException;
import com.example.alba_prokect.repository.CommentRepository;
import com.example.alba_prokect.repository.PostRepository;
import com.example.alba_prokect.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public ResponseEntity<?> createComment(Long postId, CommentRequestDto requestDto) {
        User user = SecurityUtil.getCurrentUser();
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
        );
        Comment save = new Comment(user, post.getId(), requestDto);
        commentRepository.saveAndFlush(save);
        return new ResponseEntity<>(new CommentResponseDto(save), HttpStatus.OK);
    }
}
