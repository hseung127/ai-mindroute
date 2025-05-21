package com.example.aimindroute.controller;

import com.example.aimindroute.dto.QuestionCreateRequestDto;
import com.example.aimindroute.dto.QuestionResponseDto;
import com.example.aimindroute.entity.question.Question;
import com.example.aimindroute.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;

@RestController
@RequestMapping("/admin/question")
@RequiredArgsConstructor
public class QuestionAdminController {
    private final QuestionService questionService;

    // 문항, 선택지 등록
    @PostMapping
    public ResponseEntity<QuestionResponseDto> createQuestion(@RequestBody QuestionCreateRequestDto dto){
        QuestionResponseDto response = questionService.createQuestion(dto);
        URI location = URI.create("/admin/questions/" + response.getId());
        return ResponseEntity
                .created(location)                     // HTTP 201 + Location 헤더 포함
                .body(response);                       // 생성된 문항 정보 반환
    }


}
