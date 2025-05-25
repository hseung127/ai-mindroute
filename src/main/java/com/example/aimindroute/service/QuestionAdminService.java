package com.example.aimindroute.service;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.BulkQuestionCreateRequestDto;
import com.example.aimindroute.dto.ChoiceCreateDto;
import com.example.aimindroute.dto.QuestionCreateRequestDto;
import com.example.aimindroute.entity.question.Choice;
import com.example.aimindroute.entity.question.Question;
import com.example.aimindroute.entity.test.Test;
import com.example.aimindroute.repository.ChoiceRepository;
import com.example.aimindroute.repository.QuestionRepository;
import com.example.aimindroute.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionAdminService {
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;

    // 어드민 문항, 선택지 삽입
    public ApiResponse<Long> createQuestion(BulkQuestionCreateRequestDto dto) {

        // 테스트 존재 검증
        Test test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new IllegalArgumentException("해당 테스트가 없습니다."));

        List<Question> questionList = dto.getQuestions().stream()
                .map(q -> buildQuestionFromDto(q, dto.getCreateId(), test))
                .collect(Collectors.toList());

        List<Question> savedQuestions = questionRepository.saveAll(questionList);

        return ApiResponse.<Long>builder()
                .success(true)
                .message("총 " + questionList.size() + "개의 문항이 등록되었습니다.")
                .data(savedQuestions.get(0).getId())
                .build();
    }

    // Question 엔티티에 문항, 선택지 데이터 쌓기
    private Question buildQuestionFromDto(QuestionCreateRequestDto questionDto, Long createId, Test test){
        Question question = new Question();
        question.setText(questionDto.getText());
        question.setDimension(questionDto.getDimension());
        question.setCreateId(createId);
        question.setTest(test);

        for (ChoiceCreateDto choiceDto : questionDto.getChoices()) {
            Choice choice = new Choice();
            choice.setText(choiceDto.getText());
            choice.setScoreDelta(choiceDto.getScoreDelta());
            choice.setCreateId(createId);
            question.addChoice(choice); // 리스트 추가 + 연관관계 설정
        }

        return question;
    }
}
