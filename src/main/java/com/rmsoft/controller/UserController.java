package com.rmsoft.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmsoft.domain.dto.UserJoinDTO;
import com.rmsoft.domain.dto.UserLoginDTO;
import com.rmsoft.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody UserJoinDTO userJoinDTO){
		userService.join(userJoinDTO);
		return ResponseEntity.ok().body("유저등록 성공");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO){
		String token = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
		return ResponseEntity.ok().body(token);
	}
}
