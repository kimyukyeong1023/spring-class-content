package com.yonsai.Day62_20260820;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day6220260820Application {

  public static void main(String[] args) {
    SpringApplication.run(Day6220260820Application.class, args);
  }

}

/*
 * 개발자가 실행! (ctrl + f5)
 * 톰캣 실행
 * 스프링 설정파일(application.properties)동작
 * 골뱅이(어노테이션) 찾아서 객체 생성함
 * 생성한 객체들을 모아놓은 곳 (스프링 컨테이너)
 * 스프링 컨테이너에서 객체들을 꺼내서 사용하고 싶다 
   @Autowired 사용
 * 서버 대기!(고객이 요청이 올때까지!)
 */