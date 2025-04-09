package com.artu.fullstack_team_project_application.entity.postings;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserFollowId implements java.io.Serializable {
    private static final long serialVersionUID = -4195320815653977628L;

    @Column(name = "follower_id", nullable = false)
    private String followerId;

    @Column(name = "followee_id", nullable = false)
    private String followeeId;

    // 기본 생성자
    public UserFollowId() {}

    // 필드를 포함한 생성자
    public UserFollowId(String followerId, String followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserFollowId that = (UserFollowId) o;
        return Objects.equals(followerId, that.followerId) && Objects.equals(followeeId, that.followeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, followeeId);
    }
}