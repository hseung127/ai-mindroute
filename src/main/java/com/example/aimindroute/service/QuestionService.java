package com.example.aimindroute.service;


import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.QuestionCreateRequestDto;
import com.example.aimindroute.entity.question.Choice;
import com.example.aimindroute.entity.question.Question;
import com.example.aimindroute.entity.test.Test;
import com.example.aimindroute.repository.QuestionRepository;
import com.example.aimindroute.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    public ApiResponse<Long> createQuestion(QuestionCreateRequestDto dto){

        // 테스트 존재 검증
        Test test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new IllegalArgumentException("해당 테스트가 없습니다."));

        Question question = new Question();
        question.setText(dto.getText());
        question.setDimension(dto.getDimension());
        question.setCreateId(dto.getCreateId());
        question.setTest(test);

        // 각 choice에 question 설정
        List<Choice> choices = dto.getChoices().stream()
                .map(choiceDto -> {
                    Choice choice = new Choice();
                    choice.setText(choiceDto.getText());
                    choice.setScoreDelta(choiceDto.getScoreDelta());
                    choice.setCreateId(dto.getCreateId());
                    choice.setQuestion(question); // cascade
                    return choice;
                }).collect(Collectors.toList());

        question.setChoices(choices); // cascade 작동을 위한 연결
        Question savedQuestion = questionRepository.save(question);

        return ApiResponse.<Long>builder()
                .success(true)
                .message("Question이 성공적으로 생성되었습니다.")
                .data(savedQuestion.getId())
                .build();
    }
}
