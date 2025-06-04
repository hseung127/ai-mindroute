package com.example.aimindroute.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestStartRequestDto {
    private Long testId;    // 테스트 id
    private Long memberId;  // 사용자 아이디(비회원:null)
}
