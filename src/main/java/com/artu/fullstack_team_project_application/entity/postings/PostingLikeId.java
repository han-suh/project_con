package com.artu.fullstack_team_project_application.entity.postings;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PostingLikeId implements java.io.Serializable {
    private static final long serialVersionUID = 4152144586827494083L;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "post_id", nullable = false)
    private Integer postId;

}