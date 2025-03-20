package com.artu.fullstack_team_project_application.entity.events.tickets;

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
@Table(name = "event_options")
public class EventOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opt_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "date_id", nullable = false)
    private EventDate date;

    @Column(name = "opt_name", nullable = false, length = 50)
    private String optName;

    @Column(name = "opt_price", nullable = false)
    private Integer optPrice;

    @Column(name = "goal_min", nullable = false)
    private Byte goalMin;

    @Column(name = "dc_rate", nullable = false)
    private Float dcRate;

    // @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

}