package com.example.aimindroute.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorResponse {
    private Boolean success;
    private String message;
    private int status;
    private String path; // 요청한 URL (선택)

    public static ApiErrorResponse of(String message, int status, String path) {
        return ApiErrorResponse.builder()
                .success(false)
                .message(message)
                .status(status)
                .path(path)
                .build();
    }
}
