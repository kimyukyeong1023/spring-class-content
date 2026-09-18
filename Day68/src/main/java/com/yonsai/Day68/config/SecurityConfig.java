package com.yonsai.Day68.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 스프링 객체를 관리하는 Ioc컨테이너 (객체들 보관함)
// 역할 
// @Controller  - 웹 요청을 받는 역할
// @Service - 웹 실제 처리하는 역할(DB가기전 준비작업공간,AI)
// @Configuration - 설정을 하는 역할 !

@Configuration // 스프링 설정 파일이라는 표시!
public class SecurityConfig {

  // Bcrypto - 비밀번호 암호화기를 스프링 한테 등록하는 코드!
  // 비밀번호를 안전하게 처리하는 기능들이 모여있는 패키지!
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity 시큐리티설정도구) {
    시큐리티설정도구
        .authorizeHttpRequests(접근권한설정도구 ->

        접근권한설정도구.
        // 로그인 없이 들어갈 수있는 사이트 경로!
            requestMatchers("/", "/signup").permitAll()
            // 그 외에 나머지 전부 로그인 필요하다!
            .anyRequest()
            .authenticated())

        // 로그인 안 되어있으면 이 페이지로 자동 이동!
        // 시큐리티가 만든 기본 로그인 페이지!
        .formLogin(로그인설정도구 -> 로그인설정도구.permitAll())

        .csrf(csrf -> csrf.disable());
    return 시큐리티설정도구.build();
  }

}

// 로그인 여부와 상관없이 모든 페이지를 그냥 들어갈 수있게
// 1. SecurityFilterChain - 보안 검색대,시큐리티가드
// 2. HttpSecurity - 웹 요청의 보안 규칙을 설정하는 도구!
// 로그인여부,주소별 접근 권한, CSRF 등 설정할수있다.
// 3. authorizeHttpRequests - HTTP 요청마다 접근 권한을 설정하는 객체
// 이 주소는 누구까지 들어갈 수있지? 정하는것!

// 실행 순서
// 1. 사용자 요청 GET /admin
// 2. authorizeHttpRequests (자동)
// 3. 이 주소는 누구에 허용됐지? 확인
// 4. controller

/*
 * 실무에서는 웹 - BCryptPasswordEncoder 1순위!
 * 
 * 암호화방식에 따라서 적절한 담당자를 처리를 맡기는 관리자
 * DelegatingPasswordEncoder
 * 
 * @Bean
 * public SecurityFilterChain filterChain(HttpSecurity 시큐리티설정도구) {
 * 시큐리티설정도구
 * .authorizeHttpRequests(접근권한설정도구 ->
 * 
 * // 모든 웹 요청은 로그인 없이 그냥 통과시킴!
 * 접근권한설정도구.anyRequest().permitAll()).csrf(csrf -> csrf.disable());
 * return 시큐리티설정도구.build();
 * }
 * 
 * anyRequest() - 어떤url이든 다 허용한다.
 * permitAll() - 모든 권한을 허용한다.
 * 
 * 
 */