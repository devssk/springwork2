package com.h99.week02work.controller;

import com.h99.week02work.dto.CommentRequestDto;
import com.h99.week02work.model.Comment;
import com.h99.week02work.repository.CommentRepository;
import com.h99.week02work.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    //게시글 작성
    @PostMapping("/api/comments/p")
    public Comment creatComment(@RequestBody CommentRequestDto requestDto){
        Comment comment = new Comment(requestDto);
        return commentRepository.save(comment);
    }

    //댓글 작성
    @PostMapping("/api/reply/p")
    public Comment creatReply(@RequestBody CommentRequestDto requestDto){
        Comment reply = new Comment(requestDto);
        return commentRepository.save(reply);
    }

    //게시글 전체 불러오기
    @GetMapping("/api/comments")
    public List<Comment> readComment(){
        return commentRepository.findByCommentNotNullOrderByCreatedAtDesc();
    }

    //게시글 상세페이지에서 해당 게시글 불러오기
    @GetMapping("/api/comments/{id}")
    public Optional<Comment> readOneComment(@PathVariable Long id){
        return commentRepository.findById(id);
    }

    //댓글 불러오기
    @GetMapping("/api/reply/{title}")
    public List<Comment> readReply(@PathVariable String title){
        return commentRepository.findByTitleAndCommentNullOrderByCreatedAtDesc(title);
    }

    //댓글 수정하기
    @PutMapping("/api/reply/pu/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto){
        commentService.update(id, requestDto);
        return id;
    }

    //댓글 삭제하기
    @DeleteMapping("/api/reply/pu/{id}")
    public Long deleteComment(@PathVariable Long id){
        commentRepository.deleteById(id);
        return id;
    }
}
