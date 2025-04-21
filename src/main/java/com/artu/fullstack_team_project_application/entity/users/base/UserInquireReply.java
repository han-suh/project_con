package com.artu.fullstack_team_project_application.entity.users.base;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user_inquire_replies")
public class UserInquireReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id", nullable = false)
    private Integer replyId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "reply_id", nullable = false)
    @JsonBackReference
    private UserInquire userInquires;

    @Column(name = "inquire_id")
    private Integer inquireId;

    @Lob
    @Column(name = "reply_contents", nullable = false)
    private String replyContents;

    // @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "replied_at")
    private Instant repliedAt;

    @Column(name = "counselor_id", nullable = false)
    private String counselorId;

}