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

  private String client_id = "key";
  private String client_secret = "key";

  @GetMapping("/naver")
  public String naverLogin(String code, String state, Model model) {
    System.out.println("네이버 인가코드: " + code);
    System.out.println("네이버 state: " + state);

    사용자정보가져오는토큰발급(code);
    model.addAttribute("code", code);
    return "index";
  }

  private void 사용자정보가져오는토큰발급(String code) {
    RestClient restClient = RestClient.create();

    MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
    request.add("grant_type", "authorization_code");
    request.add("client_id", client_id);
    request.add("client_secret", client_secret);
    request.add("redirect_uri", "http://localhost:8080/naver");
    request.add("code", code);

    Map result = restClient.post()
        .uri("https://nid.naver.com/oauth2.0/token")
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
        .uri("https://openapi.naver.com/v1/nid/me")
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .body(Map.class);

    System.out.println(userInfo);
  }
}
