package com.rmsoft.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rmsoft.domain.User;
import com.rmsoft.domain.dto.UserJoinDTO;
import com.rmsoft.repository.UserRepository;
import com.rmsoft.util.AppException;
import com.rmsoft.util.ErrorCode;
import com.rmsoft.util.JwtUtil;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 관리 서비스 클래스입니다.
 * 이 클래스는 회원가입, 로그인, 조회 로직을 처리합니다.
 * 
 * @author Jeon Youngjun
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    
    /**
     * 회원가입을 처리하는 메서드
     * 
     * @param userJoinDTO 가입할 사용자 정보를 담은 DTO
     * @return 가입 성공 시 null, 실패 시 예외 발생
     * @throws AppException 회원가입 실패 시 발생하는 예외
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
    
    /**
     * 사용자 로그인을 처리하는 메서드
     * 
     * @param email    로그인할 사용자의 이메일
     * @param password 로그인할 사용자의 패스워드
     * @return 로그인 성공 시 발급된 토큰
     * @throws AppException 로그인 실패 시 발생하는 예외
     */
    public String login(String email, String password) {
        // email이 존재하지 않을 경우 예외 발생
        User selectedUser = userRepository.findByEmail(email)
                                         .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_FOUND, email + "이 없습니다"));

        // 비밀번호가 일치하지 않을 경우 예외 발생
        if (!encoder.matches(password, selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력하셨습니다.");
        }
        
        // 토큰 생성 및 반환
        String token = jwtUtil.createToken(selectedUser.getEmail(), jwtUtil.secretKey(jwtUtil.getKey()), jwtUtil.getExpireTimeMs());
        return token;
    }

    /**
     * 이메일을 통한 사용자 조회를 처리하는 메서드
     * 
     * @param email 사용자를 조회할 이메일
     * @return 조회한 사용자 객체
     */
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

}
