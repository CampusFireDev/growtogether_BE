package com.campfiredev.growtogether.bootcamp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private Long bootCampId;
    //private Long memberId;
    private String content;
    private Long parentCommentId;
}
