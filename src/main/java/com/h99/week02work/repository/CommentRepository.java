package com.h99.week02work.repository;

import com.h99.week02work.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommentNotNullOrderByCreatedAtDesc();
    List<Comment> findByTitleAndCommentNullOrderByCreatedAtDesc(String title);
}
