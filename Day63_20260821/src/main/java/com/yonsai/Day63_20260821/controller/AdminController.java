package com.yonsai.Day63_20260821.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonsai.Day63_20260821.entity.Attendance;
import com.yonsai.Day63_20260821.service.AdminService;

@Controller
public class AdminController {

  @Autowired
  AdminService 출결관리자;

  // 메뉴에서 관리자메뉴 버튼을 눌렀다!
  @GetMapping("/admin")
  public String admin(Model 상자) {

    // 학생 조회
    List<Attendance> list = 출결관리자.학생조회전부();

    // 학생 정보를 HTML로 보낸다.
    상자.addAttribute("list", list);
    // 관리자 페이지 이동!
    return "Admin";
  }

}

/*
 * 관리자 메뉴 버튼을 눌렀다(요청)
 * ↓
 * 출결한 학생들을 조회
 * ↓
 * 결과를 Admin.html 보낸다.
 * ↓
 * Admin.html 화면에 출력하기
 */