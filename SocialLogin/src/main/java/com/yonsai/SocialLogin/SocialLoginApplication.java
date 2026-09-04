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
 * = 1640dbbf1b3e0b100e3b058928921cd2
 * 
 * 카카오에서 사용자정보를 가져올 때는 2차 인증 비밀번호 필요하다
 * 카카오 시크릿 번호
 * tgu7k8mcIbNq2iaUTuz88I9vqtrHtuAq
 * 
 * 리다이렉트?
 * - 지금 주소 말고 다른 주소로 다시 이동시켜!
 * - 카카오 로그인이 끝난 뒤 카카오가 다시 보내줄 우리 서버의 주소를
 * 꼭 등록해야된다. (구글,인스타,페이스북,네이버 등등등 )
 * 
 * 우리사이트 -> 카카오로그인 -> 로그인 성공 -> 리다이텍트 URL로 돌아옴
 * 
 * gpt,재미니,클로드 장단점! 읽어보기
 * 
 * 
 */