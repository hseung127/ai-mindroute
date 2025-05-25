package com.example.aimindroute.entity.question;

import com.example.aimindroute.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import com.example.aimindroute.entity.test.Test;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Setter
    private String text;

    @Setter
    private String dimension;

    /**
     * [문항이 가진 선택지 리스트]
     *
     * - @OneToMany : 문항(Question)은 여러 개의 선택지(Choice)를 가질 수 있음 (1:N 관계)
     * - mappedBy = "question" :
     *     - 관계의 주인은 Choice 쪽의 'question' 필드
     *     - Question은 연관된 Choice를 읽고 관리만 함 (insert/update는 Choice가 주도)
     * - cascade = CascadeType.ALL :
     *     - Question 저장/삭제 시, 연관된 Choice도 함께 저장/삭제됨
     *     - ex) questionRepository.save(question) → choices도 자동 저장됨
     * - orphanRemoval = true :
     *     - choices 리스트에서 선택지를 제거하면 DB에서도 자동 삭제됨
     *     - ex) question.getChoices().remove(...) → choice 레코드도 삭제됨
     *
     * ※ 주의:
     * - 실제 DB에 물리적 FK 제약조건을 만들고 싶지 않다면,
     *   JPA 연관관계는 유지하되 DDL 설정은 none/update로 설정하거나,
     *   @JoinColumn에서 insertable = false, updatable = false 사용 가능
     */
    @Builder.Default
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Choice> choices = new ArrayList<>();

    // 연관 관계 메서드
    public void addChoice(Choice choice) {
        this.choices.add(choice); // 리스트에 추가
        choice.setQuestion(this); // 연관관계 설정
    }

}
