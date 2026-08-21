package com.yonsai.Day63_20260821.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonsai.Day63_20260821.dto.AttendanceResponseDTO;
import com.yonsai.Day63_20260821.entity.Attendance;
import com.yonsai.Day63_20260821.service.AttendanceService;

@Controller // 웹 요청 받기!
public class AttendanceController {

  // 출석 실질적인 처리를 하는 담당자 배정
  @Autowired
  AttendanceService 연세매니저;

  // 메인페이지 보여줘!
  @GetMapping("/")
  public String mainPage() {
    // 로그찍기
    System.out.println("메인페이지 먼저 보여줘!");

    // 페이지 이동
    return "Attendance";
  }

  // 버튼클릭하면 학생 이름을 출력하는 함수를 작성하시오!
  // 실행 ctrl + f5 -> 자동으로 서버가 재시작된다.
  @GetMapping("/attendance")
  public String attendancePro(String studentName ,Model 상자) {
    System.out.println("학생이름: " + studentName);

    // 연세매니저야~ 출결등록해줘!
    // ctrl + . 자동으로 함수를 만들어준다.
     AttendanceResponseDTO 출결응답= 연세매니저.출결등록(studentName);

    // 결과창페이지로 이동해! 학생이름 가져가야된다.
    상자.addAttribute("studentName", studentName);
    상자.addAttribute("출결응답", 출결응답);
  

    return "AttendanceResult";
  }



}