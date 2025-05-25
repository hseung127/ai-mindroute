package com.example.aimindroute.controller;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.BulkQuestionCreateRequestDto;
import com.example.aimindroute.service.QuestionAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/questions")
@RequiredArgsConstructor
public class QuestionAdminController {
    private final QuestionAdminService questionAdminService;

    // 문항, 선택지 등록
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createQuestion(@RequestBody BulkQuestionCreateRequestDto dto){
        ApiResponse<Long> response = questionAdminService.createQuestion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
