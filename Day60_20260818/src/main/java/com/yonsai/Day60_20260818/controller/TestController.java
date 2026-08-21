package com.yonsai.Day60_20260818.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

  // 타임리프 if문
  @GetMapping("/if")
  public String test(Model 상자) {
    System.out.println("타임리프 if문 test()");

    // html로 값 보내기
    상자.addAttribute("age", 15);
    상자.addAttribute("role",
        "user");

    상자.addAttribute("login",
        true);
    상자.addAttribute("id",
        "qwer");

    return "test";
  }

}