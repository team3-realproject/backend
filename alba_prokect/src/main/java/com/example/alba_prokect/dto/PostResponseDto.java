package com.example.alba_prokect.dto;

import com.example.alba_prokect.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String profileImage;
    private String title;
    private String content;
    private String imgUrl;
    private int postLikeNum;
    private boolean isLikePost;
    private String category;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.postId = post.getId();
        this.profileImage = post.getUser().getProfileImage();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.imgUrl = post.getImgUrl();
        this.postLikeNum = 0;
        this.isLikePost = false;
        this.category = post.getCategory();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}