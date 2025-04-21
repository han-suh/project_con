package com.artu.fullstack_team_project_application.entity.users.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "user_carts")
public class UserCart {
    @Id
    @Column(name = "cart_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Lob
    @Column(name = "product_type", nullable = false)
    private String productType;

    @Column(name = "opt_id")
    private Integer optId;

    @Column(name = "opt_count")
    private Integer optCount;

}