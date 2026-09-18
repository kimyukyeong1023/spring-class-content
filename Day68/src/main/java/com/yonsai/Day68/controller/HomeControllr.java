package com.yonsai.Day68.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonsai.Day68.service.MemberService;

@Controller
public class HomeControllr {

    @Autowired
    private MemberService service;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @PostMapping("/password")
    public String password(@RequestParam("pw") String pw) {
        System.out.println("비밀번호: " + pw);

        // 비밀번호 저장해줘!
        service.memberSave(pw);

        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("pw") String pw) {
        System.out.println("login() - 비밀번호: " + pw);
        service.login(pw);

        return "index";
    }

}
/*
 * @PostMapping("/password")
 * public String password(@RequestParam("pw") String pw) {
 * System.out.println("비밀번호: " + pw);
 * 
 * String 변경된패스워드 = encoder.encode(pw);
 * 
 * System.out.println(변경된패스워드);
 * 
 * return "index";
 * }
 * 
 * 
 * 
 */