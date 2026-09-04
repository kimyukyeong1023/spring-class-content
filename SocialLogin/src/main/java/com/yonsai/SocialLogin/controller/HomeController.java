package com.yonsai.SocialLogin.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

// 서비스를 만들 때 필요한 도구들!(라이브러리 자바클래스파일!)
// 하나의 서버에서 프로그램과 프로그램을 데이터주고받을 때 
// DB 연결 - JPA 
// 서버랑 서버끼리 인터넷을 통해서 데이터를 전달하고 교환하는 형식(JSON)

// 누구랑 통신하지?
//  내 컴퓨터 안에서 다른 프로그램과 데이터를 주고받는거냐?
//  다른 컴퓨터랑 내 컴퓨터랑 데이터를 주고 받는거냐?

// 거리
//  같은 컴퓨터 내부!?
//  인터넷을 통해 원격?!

// 도구?
//  통신을 도와주는 도구?
//  데이터를 저장/조회만 하는 도구?

@Controller
public class HomeController {

  // 로그인 요청하는 메인 페이지!
  @GetMapping("/")
  public String index() {
    return "index";
  }

  // 카카오서버에서 인증번호를 가져오면 처리하는 함수!
  // 스프링이 URL을 매칭 시키고 그 뒤에 변수명을 확인 후 자동으로
  // 저장까지 해준다. url?code=~~~~~
  // html -> 자바로 보낼때 name속성! 같은 비유!
  // 인가 코드는 여러분이 pass , 문자 인증
  // 이름,휴대폰번호,생년월일 입력하는 것 처럼 비유!
  @GetMapping("/kakao")
  public String kakaoLogin(String code, Model 상자) {
    System.out.println("넘어온 인증번호(인가코드):" + code);

    // 단순히 로그인에서 끝나는게 아니라 ! 고객의 정보를 가져와야된다.
    사용자정보가져오는토큰발급(code);

    // HTML 에서 인가코드 확인
    상자.addAttribute("code", code);
    // 페이지 이동!
    return "index";
  }

  private void 사용자정보가져오는토큰발급(String code) {
    System.out.println("토큰발급 함수: " + code);

    // 1. 보낼 값 준비(인가코드 준비)
    // -1) 자바에서 카카오로 통신 도구
    // 카카오 서버에 요청 보낼 준비
    RestClient 카카오서버보내기요청도구 = RestClient.create();

    // -2) 카카오 보낼 값들을 담을 상자를 준비한다.
    MultiValueMap<String, String> 카카오설정보들 = new LinkedMultiValueMap<>();

    카카오설정보들.add("grant_type", "authorization_code");
    카카오설정보들.add("client_id", "key");
    카카오설정보들.add("redirect_uri", "http://localhost:8080/kakao");
    카카오설정보들.add("code", code);
    // 2차 인증값도 넘겨줘야된다. (보안때문에 2차 인증 활성화를 켜놓은 상태!)
    카카오설정보들.add("client_secret", "key");

    // 2.POST요청 보내기
    Map 결과 = 카카오서버보내기요청도구
        .post() // POST요청 보내기
        .uri("https://kauth.kakao.com/oauth/token") // 어디로 보낼지
        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 보낼때 타입
        .body(카카오설정보들) // 무엇을 보낼지
        .retrieve() // 카카오 서버가 보낸 응답 받아와!
        .body(Map.class); // 받아온 응답을 어떤 형태로 꺼낼지 결정해!

    // 3. 결과받기
    String accessToken = (String) 결과.get("access_token");
    System.out.println("토큰값: " + accessToken);

    // 4. 사용자 정보 가져오기
    사용자정보가져오기(결과);
  }

  private void 사용자정보가져오기(Map 결과) {

    // 1. 카카오서버로 보낼 도구 준비
    RestClient 카카오서버보내기요청도구 = RestClient.create();
    // 2. 보내기
    String accessToken = (String) 결과.get("access_token");

    Map 사용자정보 = 카카오서버보내기요청도구
        .get()
        .uri("https://kapi.kakao.com/v2/user/me")
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .body(Map.class);

    // 3. 결과출력하기
    System.out.println();
    System.out.println(사용자정보);

  }

}

/*
 * Bad client credentials
 * - Client_id (Rest API key) 잘못된다. 인식못하거나 틀렸다!
 * - 혹은 내가 클라이언트 시크릿을 활성화(2차인증 하겠다 설정)
 * client_secret키를 꼭 첨부해서 같이 보내야된다.
 * 
 * 동의화면 안 나오는 경우!
 * 한번 로그인 진행을 하면 카카오서버 인가코드랑 토큰값 발급 했는데
 * 왜또 로그인 진행해? 일정시간이 지나면 다시 동의화면이 뜬다.
 * 
 * 톰캣!?
 * - 웹 요청 받아주는 창구!
 * 스프링?
 * - 받은 요청을 어떤 코드가 처리할 지 쉽게 관리하는 도구!
 * 
 * 스프링으로 개발하는 이유?
 * - HTTP 요청 받기
 * - URL 연결 , 객체 생성/관리
 * - DB연결, 로그인, 보안처리,예외처리
 * 
 * 개발을 하는 이유는 사람들이 접속해서 사용하는 서비스를 만들려고
 * 하는 것!(자바)
 * 
 * 자바 이용해서 카카오에 접속해야된다.
 * 서버는 24시간 돌아가는 컴퓨터!(데이터를 저장,보내주는 역할)
 * 
 * 
 * 통신 - 서로 떨어져있는 프로그램끼리 데이터를 주고 받는것!
 * 다른 서버랑 카카오서버랑 통신을 하려면 도구!(라이브러리!)
 * 라이브러리들은 개발 쉽게 하기 위해서 많은 도구들이 있다.
 * 카카오 서버에 접근하려면 이 도구를 쓸래?
 * RestClient
 * 
 * 
 * HTTP요청?
 * - 브라우저나 앱이 서버한테 "이거 해줘" 메시지 보내느것!
 * 
 * HTTP(HyperText Transfer Protocol) 나온 이유는?
 * - HyperText : 웹 문서
 * - Transfer : 전송
 * - Protocol : 통신규칙!
 * 
 * - 서로 다른 컴퓨터가 인터넷에서 데이터를 주고 받기 위해서
 * 공통 약속필요해서 나온것!
 * 
 * - 모르는걸 물어본다(요청), 대답해준다(응답)
 * 컴퓨터끼리 하니깐 규칙(HTTP)
 * HTTP 규칙
 * - 1. 무엇을 하고 싶은지?
 * 어디로? 어떤 형식? 실제 데이터 뭔지?
 * - 2. 부가 정보/규칙
 * 어떤 형식으로 보낼지?
 * 이 데이터는 어떻게 해석할지?
 * 
 * - 컴퓨터끼리 통신 해야되는데 자체적으로 해주지 못한다. 그래서
 * 도구 필요하다 카카오랑 데이터를 주고받을려고 필요한 도구!
 * RestClient
 * 사용을 하려면 객체생성(요리할 때 필요한 물건을 사오듯!)
 * 
 * RestClient.create()
 * 다른 컴퓨터랑 통신하는 도구 사용할께!(메모리에 등록!)
 * 내가 서버 입장도 되지만 다른 서버에 요청을 보내는 입장!
 * 
 * 
 * 카카오인증서버에서 필요한 값들
 * HTML에서 입력한 여러 값들 서버로 간단하게 보내는 형태
 * 기본적으로 key=value 형태로 묶어서 HTTP 요청
 * 여러개를 보내야되는 상황에서는 key=value&key=value
 * 
 * HTTP 형태가 어떻게 생겼냐면
 * header 설명 정보
 * body 실제 데이터
 * 누구한테 무엇을 원하는지?
 * 
 * MultiValueMap
 * HTTP 데이터 표현 전용도구!
 * 카카오가 필요한 데이터를 담는 도구!
 * 자바에서 여러가지 정보를 편하게 저장하기 위해서 사용하는 도구!
 * 
 * 자바는 JSON 데이터 타입을 모른다.
 * 자바는 JSON은 그냥 문자(String)로 인식!
 * 데이터를 주고받은 형태는 key:value JSON 자바는 모른다
 * 그래서 자바가 원래 알고 있는 Map구조로 바꿔야된다.
 * 카카오 서버야 인가코드 줄래? (요청) JSON 써서 보내야되는데
 * JSON 타입이 없으니 MultiValueMap key,value 저장해줘
 * 그러면 내가 자동으로 JSON 타입으로 바꿔서 카카오인증서버랑
 * 통신할께!
 * 
 * 
 * 쿼리 스트링?
 * - URL 뒤에 붙여서 서버로 보내는 데이터!
 * - GET (짧은 검색 조건) 데이터가 다 보인다.
 * - url 길이 제한! 너무 길어지면 요청을 거부한다!
 * 
 * POST - 데이터가 중요하고 많고 복잡하면
 * HTTP body 안에 저장된다.
 * 네트워크 통신을 할 때 패킷!(상자!)
 * - 데이터를 네트워크로 보낼때 잘게 나눈 작은 상자
 * 
 * 서버들끼리 데이터를 쉽게 주고 받기 위해서 어떤 형태로 작성?
 * XML,JSON
 * 
 * JSON(JavaScript Object Notation)
 * 데이터를 키:값
 * Java,Ptyhon, JS - 프로그램을 만드는 언어
 * JSON,XML - 데이터를 표현하는 형식/ 데이터를 교환하는 형식
 * Markdown - 문서 작성용 언어!
 * 
 * 
 * 
 * 카카오 로그인
 * 1. 로그인 클릭 - 카카오가 인가코드를 준다
 * 2. 인가코드를 카카오한테 다시 보낸다 -> 엑세스 토큰(진짜 출입증)
 * 3. 엑세스 토큰으로 사용자 정보 요청 -> 이름/이메일을 준다.
 * 
 */