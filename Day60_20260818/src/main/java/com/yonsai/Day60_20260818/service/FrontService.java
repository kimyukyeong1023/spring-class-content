package com.yonsai.Day60_20260818.service;

import org.springframework.stereotype.Service;

import com.yonsai.Day60_20260818.dto.Person;

// 스프링한테 이야기한다 
//  FrontController에 대한 실제 로직 처리 클래스!
//  실제 웹 요청을 처리하는 곳!

// @ 어노테이션
//  - 자바파일이 스프링에게 자기 역할을 알려주는 표시! 

// 스프링이 이 클래스의 객체를 자동으로 만들어준다.
@Service
public class FrontService {

  // 로그인을 실제 처리하는 함수
  public Person login(String id, String pw) {
    // 1. 로그 찍기
    System.out.println("FrontService - login()");
    System.out.println("id:" + id);

    // 2. DB데이터에서 id를 이용해서 조회!
    // "select * from 유저테이블 where id =?";
    // 서버가 꺼지면 자바에서 저장한 모든 내용들은 지워진다.
    // 그래서 DB에 저장하는 거다!

    // 데이터가 여러개 일수있다. 근데 자바에서 return은 하나의
    // 값만 넘긴다.
    // DTO(Data Transfer Object)
    // 데이터를 담아서 이동시키는 상자
    // 자바랑 자바끼리 데이터를 이동할때!
    // 자바에서 HTML로 데이터를 이동! Model

    // return 은 하나의 데이터 혹은 하나의 화면만 보여줘!
    return new Person();

  }
}