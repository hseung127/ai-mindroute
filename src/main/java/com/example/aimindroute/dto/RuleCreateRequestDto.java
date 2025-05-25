package com.example.aimindroute.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class RuleCreateRequestDto {
    private Long testId;            // 어떤 테스트에 속한 문항인지
    private Integer priority;       // 우선순위
    private Boolean stopAfterApply; // 이후 룰 평가를 중단할지 여부
    private Boolean visibleYn;      // 프론트 룰 적용 여부
    private Long createId;          // 관리자 ID

    private List<RuleConditionCreateDto> conditions;  // 룰 조건
    private List<RuleActionCreateDto> actions;        // 룰 행동
}
