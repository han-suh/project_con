package com.artu.fullstack_team_project_application.entity.users.base;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "search_words")
public class SearchWord {
    @Id
    @Column(name = "key_id", nullable = false)
    private Integer id;

    @Column(name = "keyword", nullable = false, length = 50)
    private String keyword;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    // @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "search_at")
    private Instant searchAt;

}