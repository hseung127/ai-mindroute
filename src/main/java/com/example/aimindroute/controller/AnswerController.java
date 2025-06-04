package com.example.aimindroute.controller;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.AnswerRequestDto;
import com.example.aimindroute.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    // 답안 제출
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> submitAnswer(@RequestBody AnswerRequestDto dto) {
        ApiResponse<Long> response = answerService.saveAnswerAndUpdateScore(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
