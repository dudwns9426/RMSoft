package com.rmsoft.util;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 애플리케이션에서 사용되는 에러 코드를 정의한 열거형 클래스입니다.
 * 각 에러 코드는 HTTP 상태 코드와 메시지를 가지고 있습니다.
 * 
 * @author Jeon Youngjun
 */
@AllArgsConstructor
@Getter
public enum ErrorCode {

    /** 이메일 중복 에러 코드 */
    EMAIL_DUPLICATED(HttpStatus.CONFLICT, "Email is duplicated."),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "Email is not founded"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "");

    /** HTTP 상태 코드 */
    private final HttpStatus httpStatus;

    /** 에러 메시지 */
    private final String message;

}