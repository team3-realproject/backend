package com.example.alba_prokect.service;

import com.example.alba_prokect.dto.MsgResponseDto;
import com.example.alba_prokect.dto.PostRequestDto;
import com.example.alba_prokect.dto.PostResponseDto;
import com.example.alba_prokect.entity.Post;
import com.example.alba_prokect.entity.User;
import com.example.alba_prokect.errorcode.CommonStatusCode;
import com.example.alba_prokect.exception.RestApiException;
import com.example.alba_prokect.repository.PostRepository;
import com.example.alba_prokect.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //작성
    @Transactional
    public ResponseEntity<?> createPost(PostRequestDto requestDto) {
        User user = SecurityUtil.getCurrentUser();

        Post post = postRepository.saveAndFlush(new Post(requestDto, user));

        return new ResponseEntity<>(new PostResponseDto(post), HttpStatus.OK);
    }
    //전체글조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAt();
        return posts.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }
    //상세조회
    public ResponseEntity<?> getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
        );
        return new ResponseEntity<>(new PostResponseDto(post), HttpStatus.OK);
    }
    //수정
    @Transactional
    public ResponseEntity<?> updatePost(Long postId, PostRequestDto requestDto) {
        User user = SecurityUtil.getCurrentUser();
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new RestApiException(CommonStatusCode.NO_ARTICLE)
        );

        if(!post.getUser().getUserId().equals(user.getUserId())){
            throw new RestApiException(CommonStatusCode.INVALID_USER_UPDATE);
        }
        post.update(requestDto);
        return new ResponseEntity<>(new PostResponseDto(post), HttpStatus.OK);
    }
    //삭제
    public ResponseEntity<?> deletePost(Long postId) {
        User user = SecurityUtil.getCurrentUser();
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new RestApiException(CommonStatusCode.NO_ARTICLE)
        );
        if(!post.getUser().getUserId().equals(user.getUserId())){
            throw new RestApiException(CommonStatusCode.INVALID_USER_DELETE);
        }
        postRepository.deleteById(postId);
        return new ResponseEntity<>(new MsgResponseDto(CommonStatusCode.DELETE_POST), HttpStatus.OK);
    }


}
