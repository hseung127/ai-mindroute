package com.example.aimindroute.entity.score;

import com.example.aimindroute.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import com.example.aimindroute.entity.test.TestSession;

@Entity
@Table(name = "score_state")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ScoreState extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private TestSession session;

    private String dimension;

    @Setter
    private int score;
}
