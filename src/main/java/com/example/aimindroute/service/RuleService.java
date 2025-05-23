package com.example.aimindroute.service;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.*;
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
public class RuleService {
    private final RuleRepository ruleRepository;
    private final RuleConditionRepository ruleConditionRepository;
    private final RuleActionRepository ruleActionRepository;
    private final TestRepository testRepository;

    public ApiResponse<Long> createRule(RuleCreateRequestDto dto){
        Test test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new IllegalArgumentException("해당 테스트가 없습니다."));

        // 룰 정보 저장
        Rule rule = Rule.builder()
                .test(test)
                .priority(dto.getPriority())
                .stopAfterApply(dto.getStopAfterApply())
                .visibleYn(dto.getVisibleYn())
                .build();
        rule.setCreateId(dto.getCreateId());
        Rule savedRule = ruleRepository.save(rule);

        // 룰 조건 저장
        List<RuleCondition> conditionList = new ArrayList<>();
        for(RuleConditionCreateDto cond : dto.getConditions()){
            RuleCondition ruleCondition = RuleCondition.builder()
                    .rule(savedRule)
                    .conditionType(cond.getConditionType())
                    .dimension(cond.getDimension())
                    .targetDimension(cond.getTargetDimension())
                    .operator(cond.getOperator())
                    .comparisonValue(cond.getComparisonValue())
                    .build();
            ruleCondition.setCreateId(dto.getCreateId());
            conditionList.add(ruleCondition);
        }
        ruleConditionRepository.saveAll(conditionList);

        // 룰 행동 저장
        List<RuleAction> actionList = new ArrayList<>();
        for(RuleActionCreateDto act : dto.getActions()){
            RuleAction ruleAction = RuleAction.builder()
                    .rule(savedRule)
                    .questionId(act.getQuestionId())
                    .actionType(act.getActionType())
                    .extraData(act.getExtraData())
                    .build();
            ruleAction.setCreateId(dto.getCreateId());
            actionList.add(ruleAction);
        }
        ruleActionRepository.saveAll(actionList);

        return ApiResponse.<Long>builder()
                .success(true)
                .message("Rule이 성공적으로 생성되었습니다.")
                .data(savedRule.getId())
                .build();
    }


}
