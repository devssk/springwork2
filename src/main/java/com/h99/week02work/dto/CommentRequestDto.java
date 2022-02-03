package com.h99.week02work.dto;

import lombok.Getter;


@Getter
public class CommentRequestDto {
    private String title;
    private String comment;
    private String reply;
    private String username;
}
