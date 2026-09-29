package com.yonsai.aws.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yonsai.aws.service.RagService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoanController {

  // openai key
  // @Value("${spring.ai.openai.api-key}")
  // private String openaiKey;

  @Autowired
  private ChatModel chatModel;

  @Autowired
  private RagService ragService;

  @GetMapping("/loanpage2")
  public String test() {

    return "loan";
  }

  @GetMapping("/loanpage")
  @ResponseBody //fetch로 받아오고 보낼때 사용, 화면이 아니고 데이터만 보냄
  public String loanPage(@RequestParam ("qus") String 질문) {

    // 1. 채팅창 생성
    ChatClient 채팅창 = ChatClient.builder(chatModel).build();

    // 서비스야! 백터DB확인하고 AI한테 보낼 정보 가져다줘!
    String 추가적인정보 = ragService.search(질문);

    // 2. 채팅 보내기
    String 결과 = 채팅창.prompt()
        .system("다음 정보를 참고해서 답변해줘! " + 추가적인정보)
        .user(질문)
        .call()
        .content();

    System.out.println(결과);
    return 결과;
  }
}

// 개발을 하다보면 주 코드들이 아니라 부가적인 개발자만 보는
// 코드들을 자동으로 작성해주는 AOP 개념 예습!