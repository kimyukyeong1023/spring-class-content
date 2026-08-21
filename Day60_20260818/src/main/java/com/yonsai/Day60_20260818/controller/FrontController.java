package com.yonsai.Day60_20260818.controller;

import com.yonsai.Day60_20260818.dto.Board;
import com.yonsai.Day60_20260818.service.BoardService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 웹 요청을 처리하는 클래스!
@Controller
public class FrontController {

  // 스프링아 니가 시작할때 만든 BoardService객체를
  // service변수에 담아줘!
  @Autowired
  BoardService service;

  // 메인 페이지 보여줘!
  @GetMapping("/")
  public String mainPage(Model 상자) {
    System.out.println("메인페이지 mainPage()");

    // 게시글을 같이 보내야된다.
    ArrayList<Board> list = service.selectAll();
    System.out.println("데이터: " + list);
    상자.addAttribute("list", list);

    // 메인 페이지 보여줄때 login결과값을 보내줘야된다.
    상자.addAttribute("login", false);
    return "main";
  }

  // 작성자의 이름을 입력하고 검색을 누르면 처리하는 함수
  @GetMapping("/post/search")
  public String search(String type, String keyword, Model 상자) {
    System.out.println("타입: " + type);
    System.out.println("검색어: " + keyword);

    ArrayList<Board> list=service.findAll(type,keyword);
    상자.addAttribute("list", list);
    return "main";
  }

  // 로그인하는 페이지!보여줘!
  @GetMapping("/index")
  public String index() {
    System.out.println("메인페이지 실행 index()");

    return "index";
  }

  // 로그인을 처리하는 메서드
  // /login url들어오면 처리할것!
  @PostMapping("/login")
  public String login(String id, String pw, Model 상자) {
    // 항상 들어온 데이터 값들은 로그를 꼭!
    System.out.println("아이디: " + id);
    System.out.println("비밀번호: " + pw);

    // 로그인 페이지 이동하기!
    return "index";
  }

}

// Controller (컨트롤러의 순기능!)
// 1. URL요청 받는다.
// 특정 주소로 요청을 하면 내가 처리하겠다 라고 등록하는것!
// 2. 로직을 처리한다(계산,판단,조회)
// 3. 응답(화면 또는 데이터)을 결정해서 돌려준다.
// return 이 화면을 보여줘라!
// return 이 데이터를 그대로 응답해줘라!

// 실제 로그인 코드
// 컨트롤러가 동작 -> 값들 가지고 온다 -> DB(Mysql)에서 데이터 가져오기
// -> 비밀번호 암호화해서 비교 -> 로그인 실패 횟수체크 -> 최근 로그인
// 한 정보 확인 -> 세션에 로그인 정보 저장

// 컨트롤러에 다 넣어놓으면 컨트롤러가 드러워진다
// 코드가 길어지고 많아져서 읽기힘듬!

// 역할을 분담
// 컨트롤러 - 요청 받고 어디로 보낼지 결정하는 담당자!
// 서비스 - 실제 로직(DB조회,계산,판단)을 처리하는 담당자!