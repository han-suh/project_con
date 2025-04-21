package com.artu.fullstack_team_project_application.entity.users.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "user_points")
public class UserPoint {
    @Id
    @Column(name = "point_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "point_value", nullable = false)
    private Integer pointValue;

    @Column(name = "point_reason", length = 50)
    private String pointReason;

    @ColumnDefault("1")
    @Column(name = "is_used", nullable = false)
    private Boolean isUsed = false;

}