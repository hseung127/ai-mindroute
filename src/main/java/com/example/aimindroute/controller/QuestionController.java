package com.example.aimindroute.controller;

import com.example.aimindroute.dto.QuestionResponseDto;
import com.example.aimindroute.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    // 클라이언트 문항, 선택지 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> getQuestion(@PathVariable Long id) {
        QuestionResponseDto dto = questionService.getQuestionById(id);
        return ResponseEntity.ok(dto);
    }
}
