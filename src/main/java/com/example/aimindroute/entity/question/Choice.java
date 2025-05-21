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
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private Question question;

    // 실제로 DB에 저장되는 컬럼 (물리적 FK 아님)
    @Column(name = "question_id")
    private Long questionId;

    @Setter
    private String text;

    @Setter
    private int scoreDelta;
}
