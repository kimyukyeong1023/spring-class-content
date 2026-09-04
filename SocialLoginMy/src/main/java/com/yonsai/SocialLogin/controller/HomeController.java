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
public class HomeController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/kakao")
  public String kakaoLogin(String code, Model model) {
    System.out.println("카카오 인가코드: " + code);
    사용자정보가져오는토큰발급(code);
    model.addAttribute("code", code);
    return "index";
  }

  private void 사용자정보가져오는토큰발급(String code) {
    RestClient restClient = RestClient.create();

    MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
    request.add("grant_type", "authorization_code");
    request.add("client_id", "key");
    request.add("redirect_uri", "http://localhost:8080/kakao");
    request.add("code", code);
    request.add("client_secret", "key");

    Map result = restClient.post()
        .uri("https://kauth.kakao.com/oauth/token")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body(request)
        .retrieve()
        .body(Map.class);

    사용자정보가져오기(result);
  }

  private void 사용자정보가져오기(Map result) {
    String accessToken = (String) result.get("access_token");

    Map userInfo = RestClient.create()
        .get()
        .uri("https://kapi.kakao.com/v2/user/me")
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .body(Map.class);

    System.out.println(userInfo);
  }
}
