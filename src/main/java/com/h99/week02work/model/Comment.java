package com.h99.week02work.model;

import com.h99.week02work.Timestamped;
import com.h99.week02work.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column
    private String comment;

    @Column
    private String reply;

    public Comment(CommentRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.comment = requestDto.getComment();
        this.reply = requestDto.getReply();
        this.username = requestDto.getUsername();
    }

    public void update(CommentRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.comment = requestDto.getComment();
        this.reply = requestDto.getReply();
        this.username = requestDto.getUsername();
    }
}
