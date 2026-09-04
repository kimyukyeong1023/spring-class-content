package com.yonsai.SocialLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonsai.SocialLogin.service.MemberService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SocialController {

    @Autowired
    MemberService 실제일처리담당;

    // 1. /login-page 요청온다!
    // 2. 스프링이 GetMapping() 매칭! 어떤 자바파일이 실행할지
    @GetMapping("/login-page")
    public String loginPage() {
        // 3. 로그 찍기
        System.out.println("SocialController - loginPage보여줘!");

        // 4. 페이지로 이동!
        return "login";
    }

    // 로그인 버튼을 클릭시 로그인성공/실패를 처리하는
    // 함수 작성!
    // 1. 스프링한테 URL매칭 정보알려주기
    @PostMapping("/login-pro")
    public String loginPro(String id, String pw, Model 상자) {

        // 2.로그 (개발자입장 데이터 잘 왔는지 볼려고!)
        System.out.println("SocialController - loginPro()실행!");
        System.out.println("넘어온 아이디:" + id);
        System.out.println("넘어온 비번:" + pw);

        // 3. 실제 일을 처리하는 서비스 부르기!
        // 꼭 응답!
        String 결과 = 실제일처리담당.로그인처리해줘(id, pw);

        // 4. 응답 (개발자입장 로그확인 필수!)
        System.out.println("응답 결과:" + 결과);

        // 5. HTML 로 결과를 보내기
        상자.addAttribute("result", 결과);

        // 6. 페이지 이동해라!
        return "login";
    }

}