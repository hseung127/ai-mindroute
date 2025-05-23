package com.example.aimindroute.entity.rule;

import com.example.aimindroute.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import com.example.aimindroute.entity.rule.Rule;

@Entity
@Table(name = "rule_condition")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RuleCondition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private Rule rule;

    @Column(name = "condition_type")
    private String conditionType;

    private String dimension;

    @Column(name = "target_dimension")
    private String targetDimension;

    private String operator;

    @Column(name = "comparison_value")
    private int comparisonValue;
}
