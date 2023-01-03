package com.example.alba_prokect.service;

import com.example.alba_prokect.dto.MsgResponseDto;
import com.example.alba_prokect.entity.Likes;
import com.example.alba_prokect.entity.Post;
import com.example.alba_prokect.entity.User;
import com.example.alba_prokect.errorcode.CommonStatusCode;
import com.example.alba_prokect.exception.RestApiException;
import com.example.alba_prokect.repository.LikesRepository;
import com.example.alba_prokect.repository.PostRepository;
import com.example.alba_prokect.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;

    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<?> postLike(Long postId) {
        User user = SecurityUtil.getCurrentUser();
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
        );
        Likes like = likesRepository.findByUserIdAndPostId(user.getId(), post.getId()).orElse(new Likes());

        if(like.getId() == null){
            Likes likes = new Likes(user, post);
            likesRepository.save(likes);
            return new ResponseEntity<>(new MsgResponseDto(CommonStatusCode.POST_LIKE), HttpStatus.OK);
        } else {
            likesRepository.deleteById(like.getId());
            return new ResponseEntity<>(new MsgResponseDto(CommonStatusCode.POST_LIKE_CANCEL), HttpStatus.OK);
        }

    }
}
