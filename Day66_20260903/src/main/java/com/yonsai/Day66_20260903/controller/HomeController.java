package com.yonsai.Day66_20260903.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yonsai.Day66_20260903.service.AiService;
import com.yonsai.Day66_20260903.service.PdfService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    PdfService pdfService;

    @Autowired
    AiService aiService;

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/embedding")
    public String embedding(Model model) {
        // 2. pdf 읽기
        boolean result = aiService.pdfReader();

        String status = result ? "임베딩완료" : "질문전에 파일을 읽으세요";
        model.addAttribute("status", status);


        return "index";
    }

    @GetMapping("/question")
    public String questionPro(String question, Model 상자) {
        // 1. 로그 찍기
        System.out.println("HomeController - questionPro()");
        System.out.println("질문확인: " + question);

        // if (result == false) {
        // // false가 오면 문서를 읽다가 에러가 나거나 토큰을 쪼갤때 문제 생겼다
        // // HTML로 고객이 화면에서 볼 수있도록 에러메시지를 같이 보내기!
        // return "index";
    

    // 3. AI를 질문 보내기
    String answer = aiService.askAi(question);

    // 4. 답변을 받으면 HTML로 보내기
    상자.addAttribute("answer",answer);

    return"index";
    }

    // 버튼을 눌렀을 때 pdf파일을 불러서 처리하는 함수!
    // 컨트롤러는 서비스한테 실행해!
    @GetMapping("/pdf")
    public String pdfPro() {
        System.out.println("HomeController - pdfPro()실행");
        pdfService.pdf자르기();
        return "index";
    }

}

// 내가만든 서비스들이 언제 시작할지 알아야된다.
// 그래서 컨트롤러가 있는거다! 고객이 버튼을 눌렀을 때 실행?
// 메인페이지가 열리면서 실행? 스크롤 내렸을때 실행?
// 언제 실행한거데? 어디를 통해서 웹을 통해서!