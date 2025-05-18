package com.example.aimindroute.entity.rule;

import com.example.aimindroute.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import com.example.aimindroute.entity.rule.Rule;
import com.example.aimindroute.entity.question.Question;

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

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private Rule rule;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String actionType;

    private String extraData;
}
