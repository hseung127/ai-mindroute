package com.example.aimindroute.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BulkQuestionCreateRequestDto {
    private Long testId;          // 어떤 테스트에 속한 문항인지
    private Long createId;        // 관리자 ID

    private List<QuestionCreateRequestDto> questions;  // 선택지 리스트
}
