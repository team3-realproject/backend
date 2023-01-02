package com.example.alba_prokect.controller;

import com.example.alba_prokect.dto.CommentRequestDto;
import com.example.alba_prokect.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto){

        return commentService.createComment(postId, requestDto);
    }

}