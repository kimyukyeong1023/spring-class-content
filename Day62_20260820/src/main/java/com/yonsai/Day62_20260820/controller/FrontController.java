package com.yonsai.Day62_20260820.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonsai.Day62_20260820.entity.Post;
import com.yonsai.Day62_20260820.repository.PostRepository;

@Controller
public class FrontController {

  // 스프링컨테이너에서 꺼내오기
  @Autowired // 담당자 배정!
  PostRepository 디비접근;

  @GetMapping("/")
  public String index(Model 상자) {
    // 로그찍기
    System.out.println("FrontController - index() start");
    // 추가 save() insert into!
    // 디비접근.save(new Post("출석 1등이다!",
    // "일찍 왔다!",
    // "20260820"));

    // 삭제
    // 디비접근.deleteAll();

    // 조회 (findAll() - 전체 조회 )
    // 빈 생성자가 필요하다 조회했을때 어떤 값을 가져올지?
    // 모른다. 전부를 조회? 하나의 행만 조회할지? 하나의 행을 조회해서
    // 특정 컬럼만 가져올지(content)
    // HTML로 보내기!
    상자.addAttribute("list", 디비접근.findAll());

    // 페이지 이동
    return "index";
  }

  @PostMapping("/add")
  public String add(String title, String content) {
    System.out.println("FrontController - add() start");
    System.out.println("title: " + title);
    System.out.println("content: " + content);

    // 각각 들어온 변수 묶는다.(생성자!)
    Post 입력한값들 = new Post(title, content, "202608201425");

    // 담당자 가져오기(Autowired)
    디비접근.save(입력한값들);

    // 요청받기 -> 생성자로 묶기-> save저장

    return "index";
  }

}