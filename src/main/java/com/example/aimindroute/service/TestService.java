package com.example.aimindroute.service;

import com.example.aimindroute.dto.TestStartResponseDto;
import com.example.aimindroute.entity.member.Member;
import com.example.aimindroute.entity.question.Question;
import com.example.aimindroute.entity.queue.QueueItem;
import com.example.aimindroute.entity.score.ScoreState;
import com.example.aimindroute.entity.test.Test;
import com.example.aimindroute.entity.test.TestSession;
import com.example.aimindroute.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final TestSessionRepository testSessionRepository;
    private final QueueItemRepository queueItemRepository;
    private final ScoreStateRepository scoreStateRepository;

    // 테스트 시작(회원)
    @Transactional
    public TestStartResponseDto startTest(Long testId, Long memberId) {
        // 테스트 존재 여부 검증
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalArgumentException("테스트 없음"));

        // 회원 존재 여부 검증
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));

        // 해당 테스트의 문항 조회
        List<Question> questions = questionRepository.findByTestIdOrderByIdAsc(testId);

        // 테스트 세션 생성
        TestSession session = createTestSession(test, member);

        // 문항 큐 및 점수 초기화
        prepareSession(session, questions);

        return TestStartResponseDto.builder()
                .testSessionId(session.getId())       // DB용 세션 PK
                .sessionId(session.getSessionId())    // 클라이언트용 UUID
                .isGuest(false)                       // 비회원여부 false
                .build();
    }

    // 테스트 시작(비회원)
    @Transactional
    public TestStartResponseDto startTest(Long testId) {
        // 테스트 존재 여부 검증
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalArgumentException("테스트 없음"));

        // 해당 테스트의 문항 조회
        List<Question> questions = questionRepository.findByTestIdOrderByIdAsc(testId);

        // 테스트 세션 생성
        TestSession session = createTestSession(test, null); // 비회원이라 member 없음

        // 문항 큐 및 점수 초기화
        prepareSession(session, questions);

        return TestStartResponseDto.builder()
                .testSessionId(session.getId())       // DB용 세션 PK
                .sessionId(session.getSessionId())    // 클라이언트용 UUID
                .isGuest(true)                        // 비회원여부 true
                .build();
    }

    private TestSession createTestSession(Test test, Member member) {
        TestSession session = TestSession.builder()
                .test(test)
                .member(member)
                .sessionId(UUID.randomUUID().toString())
                .startDate(LocalDateTime.now())
                .build();
        return testSessionRepository.save(session);
    }

    // 테스트 세션 시작 시, 준비 작업 수행
    private void prepareSession(TestSession session, List<Question> questions) {
        // 문항 큐 생성
        List<QueueItem> queue = questions.stream()
                .map(q -> QueueItem.builder()
                        .session(session)
                        .question(q)
                        .isAnswered(false)
                        .build())
                .toList();
        queueItemRepository.saveAll(queue);

        // dimension별 점수 초기화 // dimension 추출(null 제외 후 중복 제거)
        Set<String> dimensions = questions.stream()
                .map(Question::getDimension)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // 각 dimension에 대해 초기 점수 상태 생성
        for (String dim : dimensions) {
            ScoreState score = ScoreState.builder()
                    .session(session)
                    .dimension(dim)
                    .score(0)
                    .build();
            scoreStateRepository.save(score);
        }
    }

}
