package com.example.aimindroute.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChoiceCreateDto {
    private String text;       // 선택지 텍스트
    private int scoreDelta;    // 점수 변화량 (예: +1, -1)
    private Long createId;     // 관리자 ID
}
