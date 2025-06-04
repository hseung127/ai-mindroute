package com.example.aimindroute.service;

import com.example.aimindroute.dto.ChoiceResponseDto;
import com.example.aimindroute.dto.QuestionResponseDto;
import com.example.aimindroute.entity.question.Choice;
import com.example.aimindroute.entity.question.Question;
import com.example.aimindroute.repository.ChoiceRepository;
import com.example.aimindroute.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;

    // 클라이언트 문항, 선택지 조회
    @Transactional
    public QuestionResponseDto getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 문항이 없습니다: " + id));

        List<Choice> choices = choiceRepository.findByQuestion_IdOrderByIdAsc(id);

        return QuestionResponseDto.builder()
                .id(question.getId())
                .text(question.getText())
                .choices(
                        choices.stream()
                                .map(c -> new ChoiceResponseDto(c.getId(), c.getText()))
                                .collect(Collectors.toList())
                )
                .build();
    }


}
