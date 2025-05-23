package com.example.aimindroute.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RuleConditionCreateDto {
    private String conditionType;     // 조건 유형 (점수, 차이 등)
    private String dimension;         // 비교 대상 차원명 (ex: 감정형)
    private String targetDimension;   // 비교 기준 차원명 (차이 비교용)
    private String operator;          // 비교 연산자 (>= 등)
    private Integer comparisonValue;  // 비교 기준 값
}
