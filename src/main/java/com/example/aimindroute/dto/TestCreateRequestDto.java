package com.example.aimindroute.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestCreateRequestDto {
    private String title;
    private String description;
    private Boolean visibleYn;

    private Long createId; // 로그인된 관리자 ID
}
