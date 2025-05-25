package com.example.aimindroute.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuestionResponseDto {
    private Long id;
    private String text;
    private List<ChoiceResponseDto> choices;
}
