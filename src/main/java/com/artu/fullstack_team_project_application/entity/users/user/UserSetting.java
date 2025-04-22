package com.artu.fullstack_team_project_application.entity.users.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "user_settings")
public class UserSetting {

    public enum DisplayColor {Light, Dark}

    public enum Language {System, English, Korean}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id", nullable = false)
    private Integer settingId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonBackReference
    @JsonIgnoreProperties({"password", "userBirth", "gender", "createdAt", "isUsed", "dropoutAt"})
    private User user;

    @ColumnDefault("'Light'")
//    @Lob
    @Column(name = "display_color")
    @Enumerated(EnumType.STRING)
    private DisplayColor displayColor;

    @ColumnDefault("'System'")
//    @Lob
    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private Language language;

//    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "set_at")
    private Instant setAt;

}