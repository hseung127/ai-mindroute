package com.example.aimindroute.service;

import com.example.aimindroute.dto.TestCreateRequestDto;
import com.example.aimindroute.dto.TestResponseDto;
import com.example.aimindroute.entity.test.Test;
import com.example.aimindroute.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public TestResponseDto createTest(TestCreateRequestDto dto) {
        Test test = Test.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .visibleYn(dto.getVisibleYn())
                .build();

        test.setCreateId(dto.getCreateId());

        Test savedTest = testRepository.save(test);
        return TestResponseDto.fromEntity(savedTest);
    }



}
