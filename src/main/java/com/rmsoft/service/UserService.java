package com.rmsoft.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rmsoft.domain.User;
import com.rmsoft.domain.dto.UserJoinDTO;
import com.rmsoft.repository.UserRepository;
import com.rmsoft.util.AppException;
import com.rmsoft.util.ErrorCode;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 관리 서비스 클래스입니다.
 * 이 클래스는 사용자 등록, 조회 로직을 처리합니다.
 * 
 * @author Jeon Youngjun
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    /**
     * 사용자 등록을 처리하는 메서드
     * 
     * @param userJoinDTO 등록할 사용자 정보를 담은 DTO
     * @return 등록 성공 시 null, 실패 시 예외 발생
     * @throws AppException 사용자 등록 실패 시 발생하는 예외
     */
    public String join(UserJoinDTO userJoinDTO) {
        String email = userJoinDTO.getEmail();
        String password = userJoinDTO.getPassword();
        String name = userJoinDTO.getName();
        String birthDate = userJoinDTO.getBirthDate();
        
        // 중복 체크
        userRepository.findByEmail(email)
                      .ifPresent(user -> {
                          throw new AppException(ErrorCode.EMAIL_DUPLICATED, email + "는 이미 등록된 이메일입니다.");
                      });

        // 사용자 생성 및 저장
        User user = User.builder()
                        .email(email)
                        .password(encoder.encode(password))
                        .name(name)
                        .birthDate(birthDate)
                        .build();
        
        userRepository.save(user);
        return null; // 등록 성공 시 null 반환
    }
    
    public String login(String email, String password) {
    	//email 없음
    	User selectedUser = userRepository.findByEmail(email)
    				  .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_FOUND, email + "이 없습니다"));
    	
    	//password 틀림
    	if(!encoder.matches(selectedUser.getPassword(), password)) {
    		throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력하셨습니다.");
    	}
    	
    	
    	
    	return "token";
    }

    /**
     * 이메일을 통한 사용자 조회를 처리하는 메서드
     * 
     * @param email 조회할 사용자의 이메일
     * @return 조회한 사용자 객체
     */
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

}
