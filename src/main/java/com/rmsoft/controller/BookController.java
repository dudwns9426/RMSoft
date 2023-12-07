package com.rmsoft.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rmsoft.domain.BookDTO;
import com.rmsoft.service.BookService;

import lombok.RequiredArgsConstructor;

/**
 * 도서에 대한 요청을 처리하는 컨트롤러 클래스입니다. 이 클래스는 도서 등록, 수정, 조회, 삭제와 관련된 기능을 제공합니다. 작성자:
 * Jeon Youngjun
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

	private final BookService bookService;

	@PostMapping
	public ResponseEntity<String> createBook(@RequestBody BookDTO bookDTO) {
		boolean result = bookService.insertBook(bookDTO);
		if (result) {
			return ResponseEntity.ok().body("성공");
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> modifyBook(@PathVariable(name = "id") Long bookId, @RequestBody BookDTO bookDTO) {
		if (bookId == null) {
			return ResponseEntity.badRequest().body("도서 ID를 제공해야 합니다.");
		}
		boolean result = bookService.modifyBook(bookDTO);
		if (result) {
			return ResponseEntity.ok().body("수정하였습니다.");
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping
	public ResponseEntity<Map<String, List<BookDTO>>> getBookList() {
		List<BookDTO> books = bookService.getBookList();
		if (books.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(Collections.singletonMap("books", books));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> selectBookDetail(@PathVariable(name = "id") Long bookId) {
		if (bookId == null) {
			return ResponseEntity.badRequest().body("도서 ID를 제공해야 합니다.");
		}
		BookDTO bookDTO = bookService.getBookDetail(bookId);
		if (bookDTO != null) {
			return ResponseEntity.ok().body(bookDTO);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable(name = "id") Long bookId) {
		if (bookId == null) {
			return ResponseEntity.badRequest().body("도서 ID를 제공해야 합니다.");
		}
		boolean result = bookService.deleteBook(bookId);
		if (result) {
			return ResponseEntity.ok().body("삭제하였습니다.");
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
}
