package com.yonsai.openai.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// 결과값을 데이터(값) 받고 싶으면 RestController
// 결과를 페이지(화면) 받고 싶다 Controller

@RestController
public class OpenAiController {

  // 1. ChatModel 정보 가져오기
  // ChatModel gpt정보; 안에 정보가 없다! 스프링한테 달라고
  // 해야된다
  @Autowired
  ChatModel gpt정보;



  @GetMapping("/index")
  public String index() {

    // 2. AI 대화하는 도구!
    ChatClient Ai채팅창 = ChatClient
        .builder(gpt정보)
        .build();

    // 3. 질문을 입력하면 됨! AI요청할 코드작성 (질문+답변)
    String 답변 = Ai채팅창
        .prompt("내가 몇 번이라고?") // 질문
        .call() // 보내기
        .content(); // 답변

    // 4.화면에 값 보이기!
    return 답변;
  }

}
// AI실행 순서
// 1. AI연결
// 2. AI 채팅창 만들기
// 3. 질문하기
// 4. 답변 보여주기