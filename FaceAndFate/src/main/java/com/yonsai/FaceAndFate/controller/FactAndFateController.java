package com.yonsai.FaceAndFate.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yonsai.FaceAndFate.service.FaceAndFateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FactAndFateController {

  // 서비스연결
  @Autowired
  private FaceAndFateService 서비스;

  @GetMapping("/")
  public String mainPage() {
    return "index";
  }

  @PostMapping("/analyze")
  public String analyze(@RequestParam("image") MultipartFile image
                        ,String birth) {
      //1.로그 찍음
      System.out.println("컨트롤러- analyze()");
      System.out.println("파일명:" + image.getOriginalFilename());
      
      //2. 서비스한테 일 시키기
      서비스.AI질문답변처리(image, birth);

      return "index";
  }

  @PostMapping("/fortune")
public String fortune (String birth, Model model){
  LocalDate 생년월일=LocalDate.parse(birth);

  String 답변=서비스.오늘의운세(생년월일);
  model.addAttribute("msg", 답변);
return"index";
}

  
}

/*
 * 
 * 1. FaceAndFateService 자바 파일을 컨트롤러에 연결!
 * 컨트롤러랑 서비스랑 연결 X (담당자 배정!)
 * 
 * 2. gpt 정보를 저장하는 코드!
 * 3. 채팅창 만들기 하는 코드!
 * 
 * 
 */