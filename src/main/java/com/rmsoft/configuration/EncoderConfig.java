package com.rmsoft.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 암호화에 사용될 BCryptPasswordEncoder 빈을 생성하는 클래스입니다.
 * 
 * @author Jeon Youngjun
 */
@Configuration
public class EncoderConfig {

    /**
     * BCryptPasswordEncoder 빈을 생성합니다.
     * 
     * @return BCryptPasswordEncoder 빈
     */
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}