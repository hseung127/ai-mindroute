package com.example.aimindroute.entity.queue;

import com.example.aimindroute.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import com.example.aimindroute.entity.test.TestSession;
import com.example.aimindroute.entity.question.Question;

@Entity
@Table(name = "queue_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class QueueItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private TestSession session;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private int position;

    private boolean isAnswered;
}
