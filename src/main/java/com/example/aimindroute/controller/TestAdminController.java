package com.example.aimindroute.controller;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.TestCreateRequestDto;
import com.example.aimindroute.service.TestAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/test")
@RequiredArgsConstructor
public class TestAdminController {
    private final TestAdminService testAdminService; // 한 번 생성자에서 주입되면 변경되지 않는 필드. 보호 목적

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createTest(@RequestBody TestCreateRequestDto dto) {
        ApiResponse<Long> response = testAdminService.createTest(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
