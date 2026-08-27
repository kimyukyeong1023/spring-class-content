package com.yonsai.Day64_20260824.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenAiController {

  /*
   * AI에 대한 설정 정보를 가지고 있는 객체
   * properties 설정파일에 있는 정보를 ChatModel에 담아준다.
   * GenAi 스타터가 자동으로 만들어서 스프링컨테이너에 넣어준다.
   * ChatModel
   */
  @Autowired
  private ChatModel chatModel;

  @GetMapping("/")
  public String index() {

    /*
     * ChatClient
     * - AI랑 실제로 대화를 주고 받는 핵심 도구!
     * - chatModel에 넣어서 만든다
     */
    ChatClient client = ChatClient
        .builder(chatModel) // AI 설정정보를 저장하고
        .build(); // 저장된 정보를 이용해서 ChatClient
                  // 객체를 생성해라!

    // 질문을 저장 해주면 된다.
    String result = client.prompt() // ->새 요청 시작! 질문하나 물어볼께~
        .system("당신은  AI분야 전문가입니다.") // 재미니 역할
        .user("Hello?") // 사용자가 재미니한테 질문"
        .call() // 재미니한테 질문보내기! 핵심!(서버로 보내기)
        .content(); // 재미니가 답변주면 문자로 자동 변환해준다.
    // 실행순서
    // 내 자바코드 -> ChatClient(AI설정정보 + 질문) -> Gemini
    // -> 답변 -> String 문자로 바꿔서 화면에 전달한다.
    System.out.println("GenAiController - index()");
    return result;
  }

}

/*
 * spring AI를 하기 위해서 사용해야되는 것!
 * 1. GenAI 다운로드 한다.
 * 2. 4.1.x 지원을 안한다! 항상 4.0.8 , 4.0.7을 사용한다.
 * 3. properties API-key랑 모델을 설정한다.
 * 4. ChatModel(설정 정보 저장) , ChatClien(설정정보 + 질문 + 보내기)
 */