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

    /** 이메일 찾기 실패 에러 코드 */
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "Email is not found."),

    /** Request 널 포인트 예외 에러 코드 */
    NULL_POINT_EXCEPTION(HttpStatus.BAD_REQUEST, "Parameter is null."),

    /** 비밀번호 불일치 에러 코드 */
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password.");

    /** HTTP 상태 코드 */
    private final HttpStatus httpStatus;

    /** 에러 메시지 */
    private final String message;

}