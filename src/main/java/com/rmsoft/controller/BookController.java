package com.rmsoft.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmsoft.domain.BookDTO;
import com.rmsoft.service.BookService;

import lombok.RequiredArgsConstructor;

/**
 * 도서에 대한 요청을 처리하는 컨트롤러 클래스입니다.
 * 이 클래스는 도서 등록, 수정, 조회, 삭제와 관련된 기능을 제공합니다.
 * 작성자: Jeon Youngjun
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
	
	private final BookService bookService;

	@PostMapping
	public ResponseEntity<String> createBook(@RequestBody BookDTO params){
		boolean result = bookService.insertBook(params);
		if(result == true) {
			return ResponseEntity.ok("성공");
		} else {
			return ResponseEntity.badRequest().build();			
		}
	}
}
