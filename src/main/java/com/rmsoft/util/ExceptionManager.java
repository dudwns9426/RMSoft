package com.rmsoft.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 애플리케이션에서 발생하는 예외를 관리하는 클래스입니다.
 * RestControllerAdvice 어노테이션을 통해 전역적으로 예외를 처리합니다.
 * 
 * @author Jeon Youngjun
 */
@RestControllerAdvice
public class ExceptionManager {
    
    /**
     * AppException 예외가 발생했을 때 처리하는 메서드입니다.
     * 
     * @param e AppException 객체
     * @return 예외 응답 ResponseEntity
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> appExceptionHandler(AppException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }

}
