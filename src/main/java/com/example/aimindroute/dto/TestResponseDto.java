package com.example.aimindroute.dto;

import com.example.aimindroute.entity.test.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class TestResponseDto {
    private Long id;
    private String title;
    private String description;
    private Boolean visibleYn;
    private LocalDateTime createDate;

    public static TestResponseDto fromEntity(Test test) {
        return TestResponseDto.builder()
                .id(test.getId())
                .title(test.getTitle())
                .description(test.getDescription())
                .visibleYn(test.getVisibleYn())
                .createDate(test.getCreateDate())
                .build();
    }
}