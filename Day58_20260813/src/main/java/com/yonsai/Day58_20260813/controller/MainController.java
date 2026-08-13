package com.yonsai.Day58_20260813.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 웹 서버 기능 만들기 
// 아래 @컨트롤러 달기! 
@Controller
public class MainController {

// 메인페이지 보여줘!
	@GetMapping("/")
	public String main() {
		// url이 들어오면 index.html 파일 고객한테 보내줘!
		return "redirect:index.html";
	}
}