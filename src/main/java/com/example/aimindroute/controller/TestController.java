package com.example.aimindroute.controller;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.TestStartRequestDto;
import com.example.aimindroute.dto.TestStartResponseDto;
import com.example.aimindroute.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    // 클라이언트 테스트 시작
    @PostMapping("/start")
    public ResponseEntity<ApiResponse<TestStartResponseDto>> startTest(@RequestBody TestStartRequestDto dto) {
        TestStartResponseDto result = (dto.getMemberId() == null)
                ? testService.startTest(dto.getTestId()) // 비회원 테스트 시작
                : testService.startTest(dto.getTestId(), dto.getMemberId()); // 회원 테스트 시작

        return ResponseEntity.ok(ApiResponse.success("테스트 시작", result));
    }
}
