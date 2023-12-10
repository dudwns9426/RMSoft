package com.rmsoft.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmsoft.domain.User;
import com.rmsoft.domain.dto.RentDTO;
import com.rmsoft.service.BookService;
import com.rmsoft.service.RentService;
import com.rmsoft.service.UserService;
import com.rmsoft.util.AppException;
import com.rmsoft.util.ErrorCode;

import lombok.RequiredArgsConstructor;

/**
 * 도서 대여에 관련된 요청을 처리하는 컨트롤러 클래스입니다.
 * 이 클래스는 도서 대여 이력 조회, 대여 생성, 반납 처리와 관련된 기능을 제공합니다.
 * 
 * @author Jeon Youngjun
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/rents")
public class RentController {

    private final UserService userService;
    private final RentService rentService;

    /**
     * 특정 도서의 대여 이력 조회를 처리하는 메서드
     * 
     * @param bookId 조회할 도서의 ID
     * @return 대여 이력 목록 또는 비어있는 응답
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, List<RentDTO>>> getRentHistory(@PathVariable(name = "id") Long bookId) {
    	if(bookId == null) {
    		throw new AppException(ErrorCode.NULL_POINT_EXCEPTION, "잘못된 요청입니다.");
    	}
        List<RentDTO> rentHistory = rentService.getRentHistory(bookId);
        if (rentHistory.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(Collections.singletonMap("rentHistory", rentHistory));
        }
    }

    /**
     * 도서 대여를 생성하는 메서드
     * 
     * @param bookId 대여할 도서의 ID
     * @param requestBody 요청 본문에 포함된 이메일 정보
     * @return 대여 생성 성공 또는 실패 메시지
     */
    @PostMapping("/{id}")
    public ResponseEntity<String> createRent(@PathVariable(name = "id") Long bookId,
                                             @RequestBody Map<String, String> requestBody) {
    	if(bookId == null || requestBody == null) {
    		throw new AppException(ErrorCode.NULL_POINT_EXCEPTION, "잘못된 요청입니다.");
    	}
        String email = requestBody.get("email");
        User user = userService.getByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("이메일을 확인해주세요");
        }
        Long userId = user.getUserId();
        boolean result = rentService.createRent(bookId, userId);
        if (result) {
            return ResponseEntity.ok().body("대여를 성공하였습니다.");
        } else {
            return ResponseEntity.badRequest().body("대여에 실패하였습니다.");
        }
    }

    /**
     * 도서 반납을 처리하는 메서드
     * 
     * @param bookId 반납할 도서의 ID
     * @return 반납 성공 또는 실패 메시지
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateReturn(@PathVariable(name = "id") Long bookId) {
    	if(bookId == null) {
    		throw new AppException(ErrorCode.NULL_POINT_EXCEPTION, "잘못된 요청입니다.");
    	}
        boolean result = rentService.updateReturn(bookId);
        if (result) {
            return ResponseEntity.ok().body("반납을 성공하였습니다.");	
        } else {
            return ResponseEntity.badRequest().body("반납에 실패하였습니다.");
        }
    }

}
