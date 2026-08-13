package com.yonsai.Day58_20260813.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yonsai.Day58_20260813.dto.Register;

// 웹 서버 기능 만들기 
// 아래 @컨트롤러 달기! 
@Controller
public class MainController {

  // 메인페이지 보여줘!
  @GetMapping("/")
  public String main() {

    // url이 들어오면 index.html 파일 고객한테 보내줘!
    return "redirect:index.html";
  }
  // GET 조회!
  // 서버에서 데이터를 가져와! (게시글 목록,피드목록,상품목록)

  // POST 추가!
  // 서버로 데이터를 보낼떄(회원가입,상품추가,게시글추가)
  // static폴더 안에 register.html 파일 만들고
  // input태그로 id , password 입력 태그만 생성해줘

  // 데이터를 서버로 가져올 때 HTML의 name 설정 이름과
  // 자바 변수명이 반드시!!!!!! 일치해야된다.

  // 변수가 많아지면 일일이 고객이 회원가입 하는 변수들을
  // 작성해야된다. 코드가 길어진다. 효율이 떨어진다
  // 그래서 한번에 자동으로 싹다! 받을 수 있게 클래스를 쓴다.

  // 스프링이 있기 때문에 자바에서 처럼 코드를 길게 작성할
  // 필요없다 클래스 타입만 적어주면 자동으로 객체 생성 후
  // 자동으로 값도 채워준다! 단! 조건 변수명이 일치해야된다.
  @PostMapping("/register")
  public String register(Register 고객정보) {

    System.out.println("넘어온 id: " + 고객정보.getId());
    System.out.println("넘어온 pw: " + 고객정보.getPw());

    // 서버는 항상 빈 값을 넘기면 안된다!
    // 꼭 실행을 하고 나면 유저가 화면을 볼 수있도록
    // return 옆에 어느사이트로 갈지 파일경로를 작성해야된다.
    return "redirect:index.html";
  }

  @PostMapping("/login")
  public String login(String id, String pw) {

    System.out.println("로그인 id: " + id);
    System.out.println("로그인 pw: " + pw);

    return "redirect:index.html";
  }

  // 우리가 배운 자바로 코드를 작성하면 긴 코드들이
  // 너무 많다 그래서 스프링 짧게 줄여준다.
  // 자바가 HTML랑 대화를 못해요! 그래서 서버 -> 어떤 코드를
  // 실행하지 판단을 못해요! 그래서 스프링
}

/*
 * AI를 사용해서 자동으로 스프링부트 프로젝트생성
 * Agent - 나 대신 일해주는 기계!
 * 
 * 일반 AI는 채팅 일일이 하나씩 지시!
 * 일일 복붙해야된다. 그래서 불편하다!
 * 
 * Agent
 * - 어떤 작업을 할지 작업지시서를 작성한다.
 * - Markdown파일 md파일 AI들이 명확한 구조를 알 수있게
 * md파일로 준다!
 * 
 * 작업지시서를 작성할 때
 * 1. Agent가 순서대로 처리할 수있도록 번호
 * Task 1
 * "뭘할지" + "어떻게 할지" + "됐는지 어떻게 확인할지"
 * Task 2
 * 
 * 2. 버전,이름,경로 정확하게 작성해야함
 * 3. md파일에서 ```이건 실행할 명령어다! ``` 코드 블록으로
 * 분리해서 작성한다.
 * 
 */