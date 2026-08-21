package com.yonsai.Day61_20260819.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yonsai.Day61_20260819.dto.Product;
import com.yonsai.Day61_20260819.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FrontController {

  // 스프링한테 객체 저장해줘!
  @Autowired
  ProductService service;

  // 1.검색어를 입력했을 때 처리하는 함수
  @GetMapping("/product/search")
  public String productSearch(String type,
      String keyword, Model 상자) {
    // 2.로그 확인
    System.out.println("FrontController - productSearch()");
    System.out.println("타입: " + type);
    System.out.println("검색어: " + keyword);

    // 3. 서비스야! 검색어 조회해서 결과 보내줄래?
    // service.productSearch(type,keyword)실행

    // 8. ArrayList타입에 저장하기
    ArrayList<Product> 검색한결과리스트 = service.productSearch(type, keyword);

    // 9. HTML로 보내기
    상자.addAttribute("list", 검색한결과리스트);

    // 10. index페이지 이동!
    return "index";
  }

  // 1. 메인페이지 보여줘!
  @GetMapping("/")
  public String index(Model 상자) {
    // 2. 로그 찍기
    System.out.println("FrontController - index()");

    // 메인 페이지가 열리면서 ProductService
    // 저장된 데이터를 가지고 메인페이지 열고 싶다!
    // 서비스야! 저장된 상품 나한테 줘! 화면에 가져다주게!
    // 3. service.all(); 실행
    // 6. ArrayList<Product> 상품리스트 받음
    ArrayList<Product> 상품리스트 = service.all();

    // 7. HTML로 보내기
    상자.addAttribute("list", 상품리스트);

    // 8. 메인페이지 이동!
    return "index";
  }
}

// 역할을 나눠서 관리하려고!
// Controller - 요청 받기
// Dto - 데이터 전달
// Service - 실제 일 처리
// 자바-> HTML로 데이터 보낸 (Model)

// lombok (롬복)
// - 자바에서 반복해서 작성하는 코드들을 자동으로 만들어주는 라이브러리(도구)