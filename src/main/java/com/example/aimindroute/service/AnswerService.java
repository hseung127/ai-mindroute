package com.example.aimindroute.service;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.AnswerRequestDto;
import com.example.aimindroute.entity.answer.Answer;
import com.example.aimindroute.entity.question.Choice;
import com.example.aimindroute.entity.question.Question;
import com.example.aimindroute.entity.score.ScoreState;
import com.example.aimindroute.entity.test.TestSession;
import com.example.aimindroute.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final TestSessionRepository testSessionRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final AnswerRepository answerRepository;
    private final ScoreStateRepository scoreStateRepository;

    // 선택 정답 저장 및 점수 업데이트
    @Transactional
    public ApiResponse<Long> saveAnswerAndUpdateScore(AnswerRequestDto dto){
        // 세션, 질문, 선택지 존재 검증
        TestSession testSession = testSessionRepository.findById(dto.getTestSessionId())
                .orElseThrow(() -> new IllegalArgumentException("해당 세션이 없습니다."));

        Question question = questionRepository.findById(dto.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("해당 문항이 없습니다."));

        Choice choice = choiceRepository.findById(dto.getChoiceId())
                .orElseThrow(() -> new IllegalArgumentException("해당 선택지가 없습니다."));

        // 선택 정답 저장
        Answer answer = Answer.builder()
                .session(testSession)
                .question(question)
                .choice(choice)
                .build();

        answerRepository.save(answer);

        // 질문의 dimension 별 점수 찾기
        String dimension = question.getDimension(); // 질문에 dimension이 있다면
        ScoreState scoreState = scoreStateRepository
                .findBySessionIdAndDimension(testSession.getId(), dimension)
                .orElseGet(() -> ScoreState.builder() //.orElseGet(...): 없을 경우 대체값을 만들어서 사용
                        .session(testSession)
                        .dimension(dimension)
                        .score(0)
                        .build());

        // 점수 업데이트
        scoreState.setScore(scoreState.getScore() + choice.getScoreDelta());
        scoreStateRepository.save(scoreState);

        return ApiResponse.<Long>builder()
                .success(true)
                .message("답변이 등록되었습니다.")
                .data(answer.getId())
                .build();
    }

}
