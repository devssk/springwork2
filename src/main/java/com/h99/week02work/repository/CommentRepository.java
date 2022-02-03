package com.h99.week02work.repository;

import com.h99.week02work.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //코멘트가 null이 아닌것을 찾아서 생성시간순 정렬
    List<Comment> findByCommentNotNullOrderByCreatedAtDesc();
    //타이틀로 코멘트가 null인것을 찾아서 생성시간순 정렬
    List<Comment> findByTitleAndCommentNullOrderByCreatedAtDesc(String title);
}
