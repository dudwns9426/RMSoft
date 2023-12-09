package com.rmsoft.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmsoft.domain.dto.UserJoinDTO;
import com.rmsoft.domain.dto.UserLoginDTO;
import com.rmsoft.service.UserService;
import com.rmsoft.util.AppException;
import com.rmsoft.util.ErrorCode;

import lombok.RequiredArgsConstructor;

/**
 * 사용자에 관련된 요청을 처리하는 컨트롤러 클래스입니다.
 * 이 클래스는 회원가입 및 로그인과 관련된 요청을 처리합니다.
 * 
 * @author Jeon Youngjun
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    
    /**
     * 회원가입을 처리하는 메서드
     * 
     * @param userJoinDTO 가입할 사용자 정보를 담은 DTO
     * @return ResponseEntity - 성공 시 "회원가입 성공" 메시지를 담은 ResponseEntity
     * @throws AppException 잘못된 요청이 들어왔을 때 발생하는 예외
     */
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinDTO userJoinDTO) {
        if (userJoinDTO == null) {
            throw new AppException(ErrorCode.NULL_POINT_EXCEPTION, "잘못된 요청입니다.");
        }
        userService.join(userJoinDTO);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    /**
     * 사용자 로그인을 처리하는 메서드
     * 
     * @param userLoginDTO 로그인할 사용자 정보를 담은 DTO
     * @return ResponseEntity - 성공 시 발급된 토큰을 담은 ResponseEntity
     * @throws AppException 잘못된 요청이 들어왔을 때 발생하는 예외
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        if (userLoginDTO == null) {
            throw new AppException(ErrorCode.NULL_POINT_EXCEPTION, "잘못된 요청입니다.");
        }
        String token = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return ResponseEntity.ok().body(token);
    }
}