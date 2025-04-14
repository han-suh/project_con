package com.artu.fullstack_team_project_application.dto;

import com.artu.fullstack_team_project_application.entity.postings.Posting;
import com.artu.fullstack_team_project_application.entity.postings.PostingComment;
import com.artu.fullstack_team_project_application.entity.users.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostingCommentsDto {
    private Integer commentId;
    private String contents;
    private User user;
    private Posting posting;
    private String createdAt;

    // PostingComment 객체를 받는 생성자 추가
    public PostingCommentsDto(PostingComment postingComment) {
        this.commentId = postingComment.getCommentId();
        this.contents = postingComment.getContents();
        this.user = postingComment.getUser();
        this.posting = postingComment.getPost();
    }
}
