package com.example.aimindroute.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionCreateRequestDto {
    private String text;          // 문항 텍스트
    private String dimension;     // 문항 차원 (예: I/E, T/F 등)

    private List<ChoiceCreateDto> choices;  // 선택지 리스트
}
