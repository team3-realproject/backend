package com.example.alba_prokect.controller;

import com.example.alba_prokect.dto.PostRequestDto;
import com.example.alba_prokect.dto.PostResponseDto;
import com.example.alba_prokect.entity.Post;
import com.example.alba_prokect.security.SecurityUtil;
import com.example.alba_prokect.security.UserDetailsImpl;
import com.example.alba_prokect.service.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    @GetMapping
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(postId, requestDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        return postService.deletePost(postId);
    }



}
