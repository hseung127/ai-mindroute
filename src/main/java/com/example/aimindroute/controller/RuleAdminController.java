package com.example.aimindroute.controller;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.RuleCreateRequestDto;
import com.example.aimindroute.service.RuleAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/rules")
@RequiredArgsConstructor
public class RuleAdminController {
    private final RuleAdminService ruleAdminService;

    // 룰 정보, 조건, 행동 등록
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createRule(@RequestBody RuleCreateRequestDto dto){
        ApiResponse<Long> response = ruleAdminService.createRule(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
