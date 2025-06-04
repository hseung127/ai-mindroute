package com.example.aimindroute.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 1. JSON 바인딩 실패 (@Valid, @RequestBody)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(ApiErrorResponse.of("JSON 오류: " + message, 400, request.getRequestURI()));
    }

    // 2. Form 데이터 검증 실패 (ex: @Valid in @ModelAttribute)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiErrorResponse> handleBind(BindException ex, HttpServletRequest request) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(ApiErrorResponse.of("입력값 오류: " + message, 400, request.getRequestURI()));
    }

    // 3. 제약조건 실패 등
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraint(ConstraintViolationException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(ApiErrorResponse.of("검증 오류: " + ex.getMessage(), 400, request.getRequestURI()));
    }

    // 4. IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(ApiErrorResponse.of(ex.getMessage(), 400, request.getRequestURI()));
    }

    // 5. 그 외 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAll(Exception ex, HttpServletRequest request) {
        log.error("[서버 오류]", ex);
        return ResponseEntity.internalServerError().body(
                ApiErrorResponse.of("알 수 없는 오류가 발생했습니다.", 500, request.getRequestURI())
        );
    }
}
