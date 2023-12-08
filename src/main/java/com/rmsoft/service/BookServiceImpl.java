package com.rmsoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmsoft.domain.dto.BookDTO;
import com.rmsoft.mapper.BookMapper;

import lombok.RequiredArgsConstructor;

/**
 * 도서 관리 서비스의 구현체 클래스입니다.
 * 이 클래스는 도서 등록, 수정, 조회, 삭제와 관련된 비즈니스 로직을 처리합니다.
 * 
 * @author Jeon Youngjun
 */
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    
    private final BookMapper bookMapper;

    /**
     * 도서 등록을 처리하는 메서드
     * 
     * @param bookDTO 도서 정보를 담은 DTO
     * @return 등록 성공 시 true, 실패 시 false
     */
    @Override
    public boolean insertBook(BookDTO bookDTO) {
        int result = bookMapper.insertBook(bookDTO);
        return result == 1;
    }

    /**
     * 도서 수정을 처리하는 메서드
     * 
     * @param bookDTO 수정할 도서 정보를 담은 DTO
     * @return 수정 성공 시 true, 실패 시 false
     */
    @Override
    public boolean modifyBook(BookDTO bookDTO) {
        int result = bookMapper.modifyBook(bookDTO);
        return result == 1;
    }

    /**
     * 모든 도서 목록 조회를 처리하는 메서드
     * 
     * @return 도서 목록
     */
    @Override
    public List<BookDTO> getBookList() {
        return bookMapper.getBookList();
    }

    /**
     * 특정 도서 상세 정보 조회를 처리하는 메서드
     * 
     * @param bookId 조회할 도서의 ID
     * @return 조회한 도서의 상세 정보
     */
    @Override
    public BookDTO getBookDetail(Long bookId) {
        return bookMapper.getBookDetail(bookId);
    }

    /**
     * 도서 삭제를 처리하는 메서드
     * 
     * @param bookId 삭제할 도서의 ID
     * @return 삭제 성공 시 true, 실패 시 false
     */
    @Override
    public boolean deleteBook(Long bookId) {
        int result = bookMapper.deleteBook(bookId);
        return result == 1;
    }

}