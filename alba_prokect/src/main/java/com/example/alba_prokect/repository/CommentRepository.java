package com.example.alba_prokect.repository;

import com.example.alba_prokect.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
