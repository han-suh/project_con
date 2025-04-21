package com.artu.fullstack_team_project_application.entity.users.user;

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
@Table(name = "user_coupons")
public class UserCoupon {
    @Id
    @Column(name = "coupon_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "dc_price")
    private Integer dcPrice;

    @Column(name = "coupon_name", length = 100)
    private String couponName;

    @Column(name = "coupon_details")
    private String couponDetails;

    @Column(name = "requirement")
    private Boolean requirement;

    @Column(name = "end_date")
    private Instant endDate;

    @ColumnDefault("1")
    @Column(name = "is_used", nullable = false)
    private Boolean isUsed = false;

}