package com.artu.fullstack_team_project_application.entity.postings;

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
@Entity
@ToString
@SQLDelete(sql = "UPDATE postings SET is_used = true WHERE post_id = ?")
@Where(clause = "is_used = true")
@Table(name = "posting_images")
public class PostingImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id", nullable = false)
    private Integer imgId;

//    @Column(name = "post_id", nullable = false, length = 50)
//    private Integer postId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id", nullable = false, insertable = false, updatable = false) // 순환 참조 방지에 따른 user 조회 안되는걸 방지하기 위해서 insertable = false, updatable = false
    @JsonBackReference // 순환 참조를 방지하는 어노테이션
    @ToString.Exclude
    private Posting post;

    @Column(name = "img_order", nullable = false)
    private Integer imgOrder;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    // @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;




}