package com.rmsoft.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 애플리케이션에서 발생한 예외를 표현하는 사용자 정의 예외 클래스입니다.
 * RuntimeException을 상속하며, ErrorCode와 메시지를 포함합니다.
 * 
 * @author Jeon Youngjun
 */
@AllArgsConstructor
@Getter
public class AppException extends RuntimeException {

    /** 예외 코드 */
    private ErrorCode errorCode;

    /** 예외 메시지 */
    private String message;

}