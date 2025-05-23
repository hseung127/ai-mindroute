package com.example.aimindroute.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RuleActionCreateDto {
    private String actionType;   // 행동 유형 (삽입, 스킵 등)
    private Long questionId;     // 삽입 유형일 경우, 삽입할 문항id
    private String extraData;    // 추가 정보(JSON 형식)
}
