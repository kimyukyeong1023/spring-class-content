package com.yonsai.rest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @RestContorller
// HTTP(웹 요청)데이터를 바로 
// 응답하는 컨트롤러!
// 프론트가 사용할 데이터를 제공하는
// 역할

// @CrossOrigin
// - 다른 주소에서 오는 요청을 허용하겠다는 어노테이션

@CrossOrigin
@RestController
public class FrontController {

    @GetMapping("/hello")
    public String hello() {
        return "안녕하세요";
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam ("userId") String id) {

        if (id.equals("admin")) {
            return "true";
        }

        return "false";
    }
}