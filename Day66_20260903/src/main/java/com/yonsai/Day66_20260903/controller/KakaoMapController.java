package com.yonsai.Day66_20260903.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yonsai.Day66_20260903.dto.PharmacieDto;
import com.yonsai.Day66_20260903.service.KakaoMapService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class KakaoMapController {

  @Autowired
  private KakaoMapService service;

  @GetMapping("/kakao")
  public String kakao() {
    System.out.println("KakaoMapController - kakao()");

    return "kakaoMap";
  }

  @GetMapping("/kakaoData")
  public String kakaoData(Model 상자) {
    System.out.println("KakaoMapController - kakaoData()");

    // 1. 공공데이터를 이용해서 데이터 가져와서 카카오맵에 띄우기!
    List<PharmacieDto> 약국정보들 = service.kakaoData();

    // 2. HTML 보내기
    상자.addAttribute("ph", 약국정보들);

    return "kakaoMap";
  }

  // 카카오맵에서 질문을 AI로 보내기
  @GetMapping("/chatBot")
  public String chatBot(String q, Model 상자) {
    System.out.println("질문:" + q);
//순환참조 문제방지를 위해 새로운 메서드사용
    String 결과 = service.질문보내기(q);

    상자.addAttribute("answer", 결과);

    return "kakaoMap";
  }

}

/*
 * 1. 카카오 개발자 센터에 들어가서 로그인 후 플랫폼 키
 * 클릭 후 자바스크립트 키를 복사하기
 * 
 * 2. 메뉴에 카카오맵 탭에 들어가서 활성화 버튼 누르기!
 * 
 * 
 * 
 */