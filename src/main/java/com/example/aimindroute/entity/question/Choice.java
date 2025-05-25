package com.example.aimindroute.entity.question;

import com.example.aimindroute.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import com.example.aimindroute.entity.question.Question;

@Entity
@Table(name = "choice")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Choice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    // 물리적 FK 없이 연관관계만 유지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Question question;

    @Setter
    private String text;

    @Setter
    private int scoreDelta;
}
