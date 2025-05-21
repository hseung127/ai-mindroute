package com.example.aimindroute.dto;

import com.example.aimindroute.entity.question.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDto {
    private Long id;                         // 문항 id
    private String text;                     // 문항 텍스트
    private String dimension;                // 문항 차원 (예: I/E, T/F 등)
    private Long createId;                   // 관리자 ID
    private LocalDateTime createDate;        // 등록일
    private List<ChoiceResponseDto> choices; // 선택지 리스트

    public static QuestionResponseDto fromEntity(Question question) {
        return new QuestionResponseDto(
                question.getId(),
                question.getText(),
                question.getDimension(),
                question.getCreateId(),
                question.getCreateDate(),
                question.getChoices().stream()
                        .map(ChoiceResponseDto::fromEntity)
                        .collect(Collectors.toList())
        );
    }
}
