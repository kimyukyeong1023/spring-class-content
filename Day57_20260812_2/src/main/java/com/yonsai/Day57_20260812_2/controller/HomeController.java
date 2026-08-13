package com.yonsai.Day57_20260812_2.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 나는 서버를 만들겠다.
// 서버기능을 만들고 싶다 
// 아무 자바파일에 @RestController 이거 붙이면 끝!
@RestController
public class HomeController {

	// 어떤 url처리할껀데?
	// 내가 리액트로 하나의 화면을 만들면 하나의 url을 생성하고
	// 하나의 처리하는 자바코드랑 연결하면 된다 그게 spring이다!
	@GetMapping("/hi")
	public String hi() {

		// return 오른쪽에 유저에게 보낼 데이터를 적으면 된다!
		// 여러분이 만든 프론트엔드 코드로 !간다!
		return "안녕하세요! 서버입니다.";
	}

	// 메인 페이지로 접속했을 때 안녕하쇼!! 라고 유저한테
	// 메시지를 보내는 기능
	@GetMapping("/main")
	public String main() {
		return "안녕하쇼! 서버요!";
	}

	// 마이페이지 보여줘!
	// /mypage 이렇게 접속했을 때 문자로 마이페이지입니다! 보내는기능

	@GetMapping("/mypage")
	public String mypage() {
		return "마이페이지입니다!";
	}

	// 현재 실시간으로 시간을 확인
	// 나 현재 시간좀 보여주는 페이지 줘!

	@GetMapping("/date")
	public String date() {
		return LocalDateTime // 현재 실시간으로 시간을 가져오는 라이브러리
				.now() // 현재 시간가져와!
				.toString(); // date타입을 문자로 변경해서 화면 에띄운다.
	}

}

/*
 * 데이터를 달라고 요청할때는 규칙(HTTP) 
 * 
 *  요청종류
 *  GET  - 서버에게 데이터 달라고 할 때 (데이터나 웹페이지)
 *      상품목록 보여줘!
 *          내 정보 보여줘, 내 게시글 보여줘
 *          유튜브영상 보여줘! 메인페이지 보여줘(네이버, 구글, 다음)
 *          
 *  POST - 서버에게 데이터를 보내고 싶을 때 
 *         네이버 회원가입할께! 
 *         유튜브 최근목록(나중에 볼 목록)에 영상 저장해줘!
 *         장바구니에 추가해줘 , 게시글 새로 작성해줘
 *         
 * 
 *  어디로 요청할껀데? (url)
 * 
 *  서버는 요청을 알아듣고 이제 유저에게 데이터나 웹 페이지를 응답해줄수
 *  있다!
 * 
 * 
 * 
 */