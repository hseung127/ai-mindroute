package com.example.aimindroute.controller;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.QuestionCreateRequestDto;
import com.example.aimindroute.service.QuestionService;
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
    private final QuestionService questionService;

    // 문항, 선택지 등록
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createQuestion(@RequestBody QuestionCreateRequestDto dto){
        ApiResponse<Long> response = questionService.createQuestion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
