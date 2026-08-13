package com.yonsai.Day57_20260812.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//URL이 들어오면 처리할 수있도록! 간편하게 세팅을 해주는 
// 키워드!
// Controller키워드
//  브라우저에서 요청(URL)이 들어왔을 때
//  어떤 작업을 처리할지 작성하는 자바파일
//  쉽게 말하면 요청 처리 담당자!

// 자바파일이 스프링한테 알려주는 표시!
// spring initalizr  스프링 부트 프로젝트를 만들어주는 시작 도구!
// springboot  우리가 실제로 사용하는 개발환경 세팅!
//  톰캣 세팅부터 스프링세팅까지 모두 자동으로 해준다.

// @ 어노테이션 (라벨 - 단순히 표시)
// 스프링한테 알려주는 표시!이 코드가 뭔지, 어떻게 다뤄야하는지?
// @Controller
// @XXXXController
//   아!  이클래스는 웹 요청을 처리하는 클래스구나!

@RestController
public class MainController {

	// 브라우저에서 GET 요청이 들어왔을 때
	// 어떤 메서드를 실행할지 스프링한테 알려주는 어노테이션이다!
	// /hello url들어오면 스프링이 확인해서 MainController안에
	// hello 메서드를 실행해서 그 안에 작업들을 실행한다.
	@GetMapping("/hello")
	public String hello() {
		return "Hello Spring";
	}
}

// initializr -> 프로젝트생성 -> main실행 -> 톰캣서버 켜짐 
//  -> 포트열고(8080) 요청 기다린다. -> /url들어오면 -> 스프링이
//  어떤 웹 요청을 처리하는지 자바파일들을 보고 어노테이션을 확인해서
//  목록을 한번 가지고 있다가 바로 자바함수를 실행한다.

// Debug As
//  - 멈춰가면서 실행!  (값/오류 확인)

// Profile As 
//  - 성능을 측정하면서 실행 (프로그램 상태/성능을분석)