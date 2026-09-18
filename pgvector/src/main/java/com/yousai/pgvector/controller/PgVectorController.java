package com.yousai.pgvector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yousai.pgvector.service.StudentService;

@Controller
public class PgVectorController {

  @Autowired
  private StudentService service;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @PostMapping("/add")
  public String add(@RequestParam("name") String name,
      @RequestParam("age") int age,
      @RequestParam("address") String address) {
    System.out.println("PgVectorController- add()");
    System.out.println("이름:" + name);
    System.out.println("나이:" + age);
    System.out.println("주소:" + address);

    service.add(name, age, address);

    return "index";
  }

  // 검색어 입력 받으면 실행한다
  // GET /search
  @GetMapping("/search")
  public String nameSearch(@RequestParam("name") String name) {

    // 1. 로그
    System.out.println("PgVectorController- nameSearch()");
    System.out.println("검색한 이름:" + name);
    // 2. 일처리하기
    service.search(name);

    // 3. HTML로 보내기

    return "index";
  }

}
