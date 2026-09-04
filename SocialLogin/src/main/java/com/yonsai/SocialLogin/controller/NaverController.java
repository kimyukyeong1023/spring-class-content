package com.yonsai.SocialLogin.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

@Controller
public class NaverController {
  // 메인페이지는 index.html 그대로 사용함
  private String client_id = "key";
  private String client_secret = "key";

  // 네이버가 인가코드를 보내는 것!
  @GetMapping("/naver")
  public String naverLogin(String code,
      String state,
      Model model) {
    System.out.println("네이버 인가코드: " + code);
    System.out.println("네이버 state: " + state);

    사용자정보가져오는토큰발급(code);

    model.addAttribute("code", code);

    return "index";
  }

  private void 사용자정보가져오는토큰발급(String code) {
    System.out.println("토큰발급 함수 : " + code);

    // 1. 요청 도구 가져오기
    RestClient 네이버서버보내기요청도구 = RestClient.create();

    // 2. 설정정보 저장하기
    MultiValueMap<String, String> 네이버설정보들 = new LinkedMultiValueMap<>();

    네이버설정보들.add("grant_type", "authorization_code");
    네이버설정보들.add("client_id", client_id);
    네이버설정보들.add("redirect_uri", "http://localhost:8080/naver");
    네이버설정보들.add("code", code);
    // 2차 인증값도 넘겨줘야된다. (보안때문에 2차 인증 활성화를 켜놓은 상태!)
    네이버설정보들.add("client_secret", client_secret);

    // 3. 보내기
    Map 결과 = 네이버서버보내기요청도구
        .post()
        .uri("https://nid.naver.com/oauth2.0/token")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body(네이버설정보들)
        .retrieve()
        .body(Map.class);

    // 출력
    System.out.println(결과);

    // 사용자 정보를 가져오는 함수
    사용자정보가져오기(결과);

  }

  private void 사용자정보가져오기(Map 결과) {
    System.out.println("사용자 정보 가져오기 함수()");

    // 1. 요청도구 가져오기
    RestClient 네이버서버보내기요청도구 = RestClient.create();

    String accessToken = (String) 결과.get("access_token");
    // 2. 보내기
    Map 사용자정보 = 네이버서버보내기요청도구
        .get()
        .uri("https://openapi.naver.com/v1/nid/me")
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .body(Map.class);

    // 3. 결과
    System.out.println(사용자정보);

  }
}

/*
 * HTTP 요청
 * - 한통의 편지 처럼 생각!
 * - 헤더(편지봉투의 쓰는 정보 ) - 누가 보냈는지? 무슨 언어로 섰는지?
 * 인증 열쇠는 이거다(부가정보)
 * - 바디(실제 내용물)
 */