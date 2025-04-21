package com.artu.fullstack_team_project_application.entity.users.base;

import com.artu.fullstack_team_project_application.entity.users.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;

import java.time.Instant;

@Getter
@Setter
@SQLDelete(sql = "UPDATE user_inquires SET is_used = false WHERE inquire_id = ?")
@Where(clause = "is_used = true")
@Entity
@ToString
@Table(name = "user_inquires")
public class UserInquire {

    public enum InquiryState {Pending, Completed}

    public enum InquireCategory {계정, 결제, 데이터등록, 기타}

    @Id
    @Column(name = "inquire_id", nullable = false)
    private Integer inquireId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ColumnDefault("'기타'")
//    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "inquire_category")
    private InquireCategory inquireCategory;

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
//    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "inquiry_state")

    private InquiryState inquiryState;

    @Column(name = "state_updated_at")
    private Instant stateUpdatedAt;

    @ColumnDefault("1")
    @Column(name = "is_used")
    private Boolean isUsed;

}