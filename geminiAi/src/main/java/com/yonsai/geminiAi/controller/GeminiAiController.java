package com.yonsai.geminiAi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GeminiAiController {

  // 대화이력을 저장하는 리스트
  List<Message> 이전대화목록 = new ArrayList<>();

  // 자연어
  // - 사람 말로 질문하면 사람말로 대답해준다.
  // 다양한 콘텐츠를 즉석에서 생성
  // - 코드,번역,요약,글, 이미지 생성

  // AI설정 정보를 자동으로 저장하고 있는 객체
  @Autowired
  private ChatModel ai정보;

  private ChatClient 재미니;

  @GetMapping("/")
  public String index() {
    System.out.println("메인페이지 열기!");

    return "index";
  }

  @PostMapping("/chat")
  public String chat(String msg, Model 상자) {

    // 재미니 정보 설정
    // spring ai 여러개 (클로드,gpt,재미니 - 각각 모델 다 다르다!)
    재미니 = ChatClient
        .builder(ai정보)
        .build();

    // 실제 재미니한테 채팅보내기
    String 결과 = 재미니.prompt() // 1. 질문 시작한다!
        .user(msg) // 2. 질문 작성한다
        .call() // 3. 재미니한테 전송!
        .content(); // 4. 답변 문자로 변경함!
    // 재미니 답변 HTML로 보내기
    상자.addAttribute("result", 결과);
    // 메인 페이지로 이동해!
    return "index";
  }

  // 이전 대화 목록 기억하는 함수 만들기
  @PostMapping("/history")
  public String getMethodName(String msg,Model 상자) {

      System.out.println("history: "+ msg);

      이전대화목록(msg);

      return "index";
  }
  
  public void 이전대화목록(String msg) {
    // 1. AI정보 생성
    재미니 = ChatClient
        .builder(ai정보)
        .build();

    // 2. 이번 질문을 리스트에 추가
    // Message 타입 - AI대화에서 오고 가는 한 개의 말(대화단위)
    // 누가 한 말인지 알아야된다. spring ai 도구 안에서
    // 이거는 누가 말한거야 ! 타입

    // UserMessage 타입으로 저장 (질문)
    // AssistantMessage 타입으로 저장 (AI답변)
    이전대화목록.add(new UserMessage(msg));

    // 3. AI한테 보낼때 이전 대화자체를 보내면 된다.
    String Ai답변 = 재미니.prompt()
                        .messages(이전대화목록)
                        .call()
                        .content();
    System.out.println(Ai답변);
    이전대화목록.add(new AssistantMessage(Ai답변));

  }

  // 재미니의 다양한 답변을 위해서 온도설정
  // public void 온도설정문법() {
  // // 1. 딱딱한 일관된 답변
  // // 예) 쇼핑몰 상품 설명 AI

  // ChatClient cold = 재미니.prompt()
  // .options(ChatOptions
  // .builder()
  // .temperature(0.0)
  // .build())
  // .call();

  // // 2. 자연럽스럽고 균형 잡힌 답변
  // ChatClient warm = 재미니.prompt()
  // .options(ChatOptions
  // .builder()
  // .temperature(1.0)
  // .build())
  // .call();

  // // 3. 창의적이고 자유로운 답변
  // ChatClient hot = 재미니.prompt()
  // .options(ChatOptions
  // .builder()
  // .temperature(2.0)
  // .build())
  // .call();
  // }

}
/*
 * builder() 만들 준비할께(설정)
 * 필요한 내용들을 설정
 * build() 실제 객체 생성
 * 
 * Car car = Car.builder()
 * .color("검정")
 * .engine("V6")
 * .option("선루프")
 * .build()
 */