package com.rmsoft.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rmsoft.domain.dto.BookDTO;
import com.rmsoft.service.BookService;
import com.rmsoft.util.AppException;
import com.rmsoft.util.ErrorCode;

import lombok.RequiredArgsConstructor;

/**
 * 도서에 대한 요청을 처리하는 컨트롤러 클래스입니다. 이 클래스는 도서 등록, 조회, 수정, 삭제와 관련된 기능을 제공합니다.
 * @author Jeon Youngjun
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

	private final BookService bookService;

	/**
     * 도서 등록을 처리하는 메서드
     * 
     * @param bookDTO 등록할 도서 정보를 담은 DTO
     * @return 성공 시 200 OK와 메시지, 실패 시 500 Internal Server Error
     */
    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody BookDTO bookDTO) {
    	if(bookDTO == null) {
    		throw new AppException(ErrorCode.NULL_POINT_EXCEPTION, "잘못된 요청입니다.");
    	}
        boolean result = bookService.insertBook(bookDTO);
        if (result) {
            return ResponseEntity.ok().body("도서 등록에 성공하였습니다.");
        } else {
        	return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 모든 도서 목록 조회를 처리하는 메서드
     * 
     * @return 도서 목록이 담긴 ResponseEntity (204 No Content 또는 200 OK)
     */
    @GetMapping
    public ResponseEntity<Map<String, List<BookDTO>>> getBookList() {
        List<BookDTO> books = bookService.getBookList();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(Collections.singletonMap("books", books));
        }
    }

    /**
     * 도서 정보 수정을 처리하는 메서드
     * 
     * @param bookId  수정할 도서의 ID
     * @param bookDTO 수정할 도서 정보를 담은 DTO
     * @return 성공 시 200 OK와 메시지, 실패 시 500 Internal Server Error
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> modifyBook(@PathVariable(name = "id") Long bookId, @RequestBody BookDTO bookDTO) {
        if (bookId == null || bookDTO == null) {
        	throw new AppException(ErrorCode.NULL_POINT_EXCEPTION, "잘못된 요청입니다.");
        }
        boolean result = bookService.modifyBook(bookDTO);
        if (result) {
            return ResponseEntity.ok().body("도서 정보를 수정하였습니다.");
        } else {
        	return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 특정 도서 상세 정보 조회를 처리하는 메서드
     * 
     * @param bookId 조회할 도서의 ID
     * @return 조회한 도서의 상세 정보가 담긴 ResponseEntity (200 OK 또는 404 Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> selectBookDetail(@PathVariable(name = "id") Long bookId) {
        if (bookId == null) {
        	throw new AppException(ErrorCode.NULL_POINT_EXCEPTION, "잘못된 요청입니다.");
        }
        BookDTO bookDTO = bookService.getBookDetail(bookId);
        if (bookDTO != null) {
            return ResponseEntity.ok().body(bookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 도서 삭제를 처리하는 메서드
     * 
     * @param bookId 삭제할 도서의 ID
     * @return 성공 시 200 OK와 메시지, 실패 시 500 Internal Server Error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") Long bookId) {
        if (bookId == null) {
        	throw new AppException(ErrorCode.NULL_POINT_EXCEPTION, "잘못된 요청입니다.");
        }
        boolean result = bookService.deleteBook(bookId);
        if (result) {
            return ResponseEntity.ok().body("도서를 삭제하였습니다.");
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}

