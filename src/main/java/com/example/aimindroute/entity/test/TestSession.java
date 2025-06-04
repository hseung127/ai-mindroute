package com.example.aimindroute.entity.test;
import com.example.aimindroute.entity.member.Member;

import com.example.aimindroute.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "test_session")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestSession extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String sessionId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = true)
    private Member member;

    @Setter
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Setter
    @Column(name = "start_date")
    private java.time.LocalDateTime startDate;

    @Setter
    @Column(name = "finish_date")
    private java.time.LocalDateTime finishDate;
}
