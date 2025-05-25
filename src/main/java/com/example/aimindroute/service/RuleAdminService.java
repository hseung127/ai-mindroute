package com.example.aimindroute.service;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.*;
import com.example.aimindroute.entity.question.Choice;
import com.example.aimindroute.entity.question.Question;
import com.example.aimindroute.entity.rule.Rule;
import com.example.aimindroute.entity.rule.RuleAction;
import com.example.aimindroute.entity.rule.RuleCondition;
import com.example.aimindroute.entity.test.Test;
import com.example.aimindroute.repository.RuleActionRepository;
import com.example.aimindroute.repository.RuleConditionRepository;
import com.example.aimindroute.repository.RuleRepository;
import com.example.aimindroute.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RuleAdminService {
    private final RuleRepository ruleRepository;
    private final RuleConditionRepository ruleConditionRepository;
    private final RuleActionRepository ruleActionRepository;
    private final TestRepository testRepository;

    public ApiResponse<Long> createRule(RuleCreateRequestDto dto) {
        // 테스트 존재 검증
        Test test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new IllegalArgumentException("해당 테스트가 없습니다."));

        // 룰 정보 생성
        Rule rule = Rule.builder()
                .test(test)
                .priority(dto.getPriority())
                .stopAfterApply(dto.getStopAfterApply())
                .visibleYn(dto.getVisibleYn())
                .build();
        rule.setCreateId(dto.getCreateId());
        Rule savedRule = ruleRepository.save(rule);

        // 룰 조건 생성
        for (RuleConditionCreateDto condDto : dto.getConditions()) {
            buildRuleConditionFromDto(condDto, dto.getCreateId(), rule);
        }

        // 룰 행동 생성
        for(RuleActionCreateDto actDto : dto.getActions()){
            buildRuleActionFromDto(actDto, dto.getCreateId(), rule);
        }

        // 저장(조건/행동도 자동 저장)
        ruleRepository.save(rule);

        return ApiResponse.<Long>builder()
                .success(true)
                .message("Rule이 성공적으로 생성되었습니다.")
                .data(savedRule.getId())
                .build();
    }

    // RuleCondition 엔티티에 룰 조건 데이터 쌓기
    private void buildRuleConditionFromDto(RuleConditionCreateDto conditionDto, Long createId, Rule rule){
        RuleCondition ruleCondition = RuleCondition.builder()
                .conditionType(conditionDto.getConditionType())
                .dimension(conditionDto.getDimension())
                .targetDimension(conditionDto.getTargetDimension())
                .operator(conditionDto.getOperator())
                .comparisonValue(conditionDto.getComparisonValue())
                .build();
        ruleCondition.setCreateId(createId);
        rule.addRuleCondition(ruleCondition); // 리스트 추가 + 연관관계 설정
    }

    // RuleAction 엔티티에 룰 행동 데이터 쌓기
    private void buildRuleActionFromDto(RuleActionCreateDto actionDto, Long createId, Rule rule) {
        RuleAction ruleAction = RuleAction.builder()
                .questionId(actionDto.getQuestionId())
                .actionType(actionDto.getActionType())
                .extraData(actionDto.getExtraData())
                .build();
        ruleAction.setCreateId(createId);
        rule.addRuleAction(ruleAction); // 리스트 추가 + 연관관계 설정
    }


}
