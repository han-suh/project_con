package com.artu.fullstack_team_project_application.entity.users.base;

import com.artu.fullstack_team_project_application.entity.users.user.User;
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
@Table(name = "user_inquires")
public class UserInquire {

    public enum InquiryState {Pending, Completed}

    @Id
    @Column(name = "inquire_id", nullable = false)
    private Integer inquireId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ColumnDefault("'기타'")
    @Lob
    @Column(name = "inquire_category")
    private String inquireCategory;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "inquiry_img_url")
    private String inquiryImgUrl;

    // @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("'Pending'")
    @Lob
    @Column(name = "inquiry_state")
    private InquiryState inquiryState;

    @Column(name = "state_updated_at")
    private Instant stateUpdatedAt;

}