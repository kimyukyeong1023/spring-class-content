package com.yonsai.SocialLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialLoginApplication {

  public static void main(String[] args) {
    SpringApplication.run(SocialLoginApplication.class, args);
  }
}
/*
 * OAuth?
 * - 다른 서비스의 계정 정보를 이용해서 로그인/권한인증 처리하는 방식
 * 
 * REST API
 * = 웹에서 주소(URL) + HTTP명령어로 데이터를 주고 받는 약속 방식
 * = 무엇을 어떻게 할지!
 * 
 * 카카오 API KEY (비밀번호)
 * = 

   카카오에서 사용자정보를 가져올 때는 2차 인증 비밀번호 필요하다
   카카오 시크릿 번호 
   
 * 
  리다이렉트?
    - 지금 주소 말고 다른 주소로 다시 이동시켜!
 * 
 */