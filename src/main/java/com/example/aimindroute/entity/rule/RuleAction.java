package com.example.aimindroute.entity.rule;

import com.example.aimindroute.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rule_action")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RuleAction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Rule rule;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "extra_data", columnDefinition = "TEXT")
    private String extraData;
}
