package com.yousai.pgvector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PgvectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgvectorApplication.class, args);
	}
}
/*
 * 도커(Docker)
 * - 프로그램 실행하는 환경을 통째로 포장해서 어디서든지 똑같이
 * 실행하게 해주는 도구
 * - 나오게된 이유는 내 컴퓨터에서는 잘 되는데 다른 컴퓨터에서
 * 안되는 문제때문에 나오게 되었다.
 * 
 * 
 * properties 설정
 * .점으로 연결 설정
 * 
 * yaml 설정
 * 들여쓰기 구조 (4칸)
 * 
 * 비밀번호들이나 중요한 키들은 환경변수에 설정 하는게 원칙
 * 환경변수명이랑 야물파일의 변수명이랑 똑같아야 스프링부트가
 * 찾아온다.
 * 
 * BeanCreationException
 * - 스프링이 객체를 생성하는 중에 에러가 발생했다!
 * 
 * PostgreSql DBeaver에서 스크립트 창입니다!
 * username으로 <username> 되어있다!
 * 
 * 도커
 * - 내가 설치하고 싶은 프로그램들이 있으면 제일 먼저 도커!
 * - 검색 해서 pull 다운로드한다!
 * 
 * Image
 * - 프로그램을 실행하기 위한 포장본
 * - 프로그램(코드) + 필요한 실행환경을 미리 묶는것!(밀키트)
 * 
 * Container
 * - 우리가 받은 이미지를 실행하는 공간!
 * 
 * 
 * UnauthorizedException: 401
 * 1. apiKey 잘못됨
 */
