package com.yonsai.Day57_20260812.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 웹에서 상품과 관련된 웹 요청이 오면 처리하는 클래스야!
@RestController
public class ProductController {
// Mapping 
//  - url이 들어오면 스프링이 url을 보고 어떤 자바
//   메서드(함수)를 실행할지 연결하는 것!

// 전체 상품 조회
	@GetMapping("/all")
	public String all() {
		return "전체 상품 조회 완료";
	}

// 상세 페이지조회
	@GetMapping("/detail")
	public String detail() {
		return "상세 페이지 조회 완료";
	}
}