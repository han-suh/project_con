package com.artu.fullstack_team_project_application.entity.events.reviews;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@ToString
@Entity
@Table(name = "event_review_images")
public class EventReviewImage {
    @Id
    @Column(name = "image_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    // @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_at", nullable = false)
    private Instant createAt;

}