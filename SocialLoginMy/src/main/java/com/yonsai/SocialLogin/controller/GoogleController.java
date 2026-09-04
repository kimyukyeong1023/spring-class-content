package com.yonsai.SocialLogin.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

@Controller
public class GoogleController {

  private static final String CLIENT_ID = "key";
  private static final String CLIENT_SECRET = "key";
  private static final String REDIRECT_URI = "http://localhost:8080/google";

  @GetMapping("/google")
  public String googleLogin(@RequestParam String code, Model model) {
    System.out.println("구글 인가코드: " + code);

    String accessToken = getAccessToken(code);
    Map<String, Object> userInfo = getUserInfo(accessToken);

    String email = (String) userInfo.get("email");
    String nickname = (String) userInfo.get("name");

    System.out.println("구글 사용자 이메일: " + email);
    System.out.println("구글 사용자 닉네임: " + nickname);
    System.out.println("구글 사용자 정보 전체: " + userInfo);

    model.addAttribute("googleCode", code);
    model.addAttribute("googleUser", userInfo);

    return "index";
  }

  private String getAccessToken(String code) {
    RestClient restClient = RestClient.create();

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("code", code);
    params.add("client_id", CLIENT_ID);
    params.add("client_secret", CLIENT_SECRET);
    params.add("redirect_uri", REDIRECT_URI);
    params.add("grant_type", "authorization_code");

    Map<String, Object> result = restClient.post()
        .uri("https://oauth2.googleapis.com/token")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body(params)
        .retrieve()
        .body(Map.class);

    if (result == null || result.get("access_token") == null) {
      throw new IllegalStateException("구글 토큰 발급 실패: " + result);
    }

    return (String) result.get("access_token");
  }

  private Map<String, Object> getUserInfo(String accessToken) {
    RestClient restClient = RestClient.create();

    return restClient.get()
        .uri("https://www.googleapis.com/oauth2/v2/userinfo")
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .body(Map.class);
  }
}