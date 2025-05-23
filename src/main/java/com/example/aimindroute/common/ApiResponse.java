package com.example.aimindroute.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private Boolean success;   // 성공 여부
    private String message;    // 사용자에게 보여줄 메시지 (optional)
    private T data;            // 실제 응답 데이터 (DTO, ID, 리스트 등)
}