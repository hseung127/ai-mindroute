package com.example.aimindroute.entity.rule;

import com.example.aimindroute.entity.common.BaseEntity;
import com.example.aimindroute.entity.question.Choice;
import jakarta.persistence.*;
import lombok.*;
import com.example.aimindroute.entity.test.Test;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rule")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Rule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Test test;

    private int priority;

    private boolean stopAfterApply;

    @Column(name = "visible_yn")
    private boolean visibleYn;

    @Builder.Default
    @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RuleCondition> conditions = new ArrayList<>();

    // 연관 관계 메서드
    public void addRuleCondition(RuleCondition condition) {
        this.conditions.add(condition); // 리스트에 추가
        condition.setRule(this); // 연관관계 설정
    }

    @Builder.Default
    @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RuleAction> actions = new ArrayList<>();

    // 연관 관계 메서드
    public void addRuleAction(RuleAction action) {
        this.actions.add(action); // 리스트에 추가
        action.setRule(this); // 연관관계 설정
    }



}
