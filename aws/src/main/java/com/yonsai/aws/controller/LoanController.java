package com.yonsai.aws.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yonsai.aws.service.RagService;

@Controller
public class LoanController {

  // openai key
  // @Value("${spring.ai.openai.api-key}")
  // private String openaiKey;

  @Autowired
  private ChatModel chatModel;

  @Autowired
  private RagService ragService;

  @GetMapping("/loanpage")
  public String loanPage() {

    // 1. 채팅창 생성
    ChatClient 채팅창 = ChatClient.builder(chatModel).build();

    // 서비스야! 백터DB확인하고 AI한테 보낼 정보 가져다줘!
    String 추가적인정보 = ragService.search("금리 낮은 대출상품 추천해줘");

    // 2. 채팅 보내기
    String 결과 = 채팅창.prompt()
        .system("다음 정보를 참고해서 답변해줘! " + 추가적인정보)
        .user("금리 낮은 대출상품 추천해줘")
        .call()
        .content();

    System.out.println(결과);
    return "loan";
  }
}

// 개발을 하다보면 주 코드들이 아니라 부가적인 개발자만 보는
// 코드들을 자동으로 작성해주는 AOP 개념 예습!