package com.h99.week02work.controller;

import com.h99.week02work.dto.CommentRequestDto;
import com.h99.week02work.model.Comment;
import com.h99.week02work.repository.CommentRepository;
import com.h99.week02work.security.UserDetailsImpl;
import com.h99.week02work.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/api/comments/p")
    public Comment creatComment(@RequestBody CommentRequestDto requestDto){
        Comment comment = new Comment(requestDto);
        return commentRepository.save(comment);
    }

    @PostMapping("/api/reply/p")
    public Comment creatReply(@RequestBody CommentRequestDto requestDto){
        Comment reply = new Comment(requestDto);
        return commentRepository.save(reply);
    }

    @GetMapping("/api/comments")
    public List<Comment> readComment(){
        return commentRepository.findByCommentNotNullOrderByCreatedAtDesc();
    }

    @GetMapping("/api/comments/{id}")
    public Optional<Comment> readOneComment(@PathVariable Long id){
        return commentRepository.findById(id);
    }

    @GetMapping("/api/reply/{title}")
    public List<Comment> readReply(@PathVariable String title){
        return commentRepository.findByTitleAndCommentNullOrderByCreatedAtDesc(title);
    }

    @PutMapping("/api/reply/pu/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto){
        commentService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/reply/pu/{id}")
    public Long deleteComment(@PathVariable Long id){
        commentRepository.deleteById(id);
        return id;
    }
}
