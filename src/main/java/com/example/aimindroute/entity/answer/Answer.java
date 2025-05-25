package com.example.aimindroute.entity.answer;

import com.example.aimindroute.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import com.example.aimindroute.entity.test.TestSession;
import com.example.aimindroute.entity.question.Question;
import com.example.aimindroute.entity.question.Choice;

@Entity
@Table(name = "answer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private TestSession session;

    @ManyToOne
    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Question question;

    @ManyToOne
    @JoinColumn(name = "choice_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Choice choice;
}
