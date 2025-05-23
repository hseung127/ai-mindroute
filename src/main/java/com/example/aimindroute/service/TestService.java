package com.example.aimindroute.service;

import com.example.aimindroute.common.ApiResponse;
import com.example.aimindroute.dto.TestCreateRequestDto;
import com.example.aimindroute.entity.test.Test;
import com.example.aimindroute.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final TestRepository testRepository;

    public ApiResponse<Long> createTest(TestCreateRequestDto dto) {
        Test test = Test.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .visibleYn(dto.getVisibleYn())
                .build();

        test.setCreateId(dto.getCreateId());

        Test savedTest = testRepository.save(test);

        return ApiResponse.<Long>builder()
                .success(true)
                .message("Test가 성공적으로 생성되었습니다.")
                .data(savedTest.getId())
                .build();
    }



}
