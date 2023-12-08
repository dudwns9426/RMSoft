package com.rmsoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmsoft.domain.dto.RentDTO;
import com.rmsoft.domain.dto.RentRequest;
import com.rmsoft.mapper.RentMapper;

import lombok.RequiredArgsConstructor;

/**
 * 도서 대여 관리 서비스 클래스입니다.
 * 이 클래스는 대여 이력 조회, 대여 생성, 반납 처리와 관련된 비즈니스 로직을 처리합니다.
 * 
 * @author Jeon Youngjun
 */
@RequiredArgsConstructor
@Service
public class RentServiceImpl implements RentService {

    private final RentMapper rentMapper;

    /**
     * 특정 도서의 대여 이력 조회를 처리하는 메서드
     * 
     * @param bookId 조회할 도서의 ID
     * @return 대여 이력 목록
     */
    @Override
    public List<RentDTO> getRentHistory(Long bookId) {
        return rentMapper.getRentHistory(bookId);
    }

    /**
     * 도서 대여를 생성하는 메서드
     * 
     * @param bookId 대여할 도서의 ID
     * @param userId 대여를 신청한 사용자의 ID
     * @return 대여 생성 성공 시 true, 실패 시 false
     */
    @Override
    public boolean createRent(Long bookId, Long userId) {
        RentRequest rentRequest = new RentRequest(bookId, userId);
        int result = rentMapper.createRent(rentRequest);
        return result == 1;
    }

    /**
     * 도서 반납을 처리하는 메서드
     * 
     * @param bookId 반납할 도서의 ID
     * @return 반납 성공 시 true, 실패 시 false
     */
    @Override
    public boolean updateReturn(Long bookId) {
        int result = rentMapper.updateReturn(bookId);
        return result == 1;
    }

}