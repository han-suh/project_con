package com.artu.fullstack_team_project_application.entity.users.base;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "hashtags")
public class Hashtag {
    @Id
    @Column(name = "tag_id", nullable = false)
    private Integer id;

    @Column(name = "tagword", nullable = false, length = 50)
    private String tagword;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_in")
    private Instant createdIn;

}