package com.example.aimindroute.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestStartResponseDto {
    private Long testSessionId; // 테스트 sessionId
    private String sessionId;   // 비회원/회원 sessionId
    private boolean isGuest;    // 비회원 여부 표시
}
