package com.yousai.pgvector.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yousai.pgvector.mcp.DiceMcpTool;

@Controller
public class McpController {

  @Autowired
  private ChatModel chatModel;

  // 내가 직접 만든 MCP 파일을 가져오기
  @Autowired
  private DiceMcpTool diceMcpTool;

  @GetMapping("/mcp2")
  public String chat(@RequestParam("msg") String msg) {
    System.out.println("메시지 : " + msg);

    ChatClient chatClient = ChatClient
        .builder(chatModel)
        .build();

    String 결과 = chatClient.prompt()
        .system("당신은 친절한 도우미 입니다. 사용자가 주사위 굴려라하면 반드시 roll_dice도구를 호출해라! 절대 직접 숫자를 지어내지마세요!")
        .user(msg)
        .tools(diceMcpTool)
        .call()
        .content();
    System.out.println("결과: " + 결과);

    return "index";
  }

  // 실무에서는 AI가 접근할 수 있는 특정 폴더를 꼭! 지정
  //

  /*
   * 
   * AI는 자바코드를 보는게 아니라 Spring AI가 자동으로 만들어주는
   * JSON 스키마를 보고 실행한다.
   * {
   * "name": "read_file",
   * "description": "파일 내용을 읽어온다",
   * "parameters": {
   * "fileName": {
   * "type": "string",
   * "description": "파일 이름 (예: hello.txt)",
   * "required": true
   * }
   * }
   * }
   * 
   * yaml 파일에 mcp에 추가설정하기
   * protocol: STREAMABLE
   * - 외부 프로그램이 HTTP로 우리 서버에 접속해서 도구를 사용할수
   * 있도록 추가!
   * 
   * 
   */

  // McpTool - 이 함수는 이런일을 해요!
  @McpTool(name = "read_file", description = "지정된 경로의 텍스트 파일 내용을 읽어온다")
  public void readFile(
      // 이 함수를 실행할때 이런 재료를 넣어요!
      // 그냥 변수만 쓰면 값을 넣으라는건지 경로를 넣어야되는건지 모른다
      @McpToolParam(description = "읽을 파일의 전체 경로(예:c/fullstack/hello.txt)", required = true) String path) {

    try {
      Files.readString(Path.of(path));

    } catch (IOException e) {

      e.printStackTrace();
    }

  }
}

/*
 * MCP(Model Context Protocol)
 * - AI모델과 외부 도구(프로그램) 을 사용할 수있게 해주는 연결 규칙!
 * - 노션,슬랙,구글 드라이브 ,파일 읽기 및 수정,이메일
 * - AI용 사용 설명서
 * 
 * MCP를 하려면 도구! 다운로드
 * implementation 'org.springframework.ai:spring-ai-starter-mcp-server-webmvc'
 * 
 * AI에게 기능을 제공하는 MCP 서버!
 * mcp:
 * server:
 * name: hotel-search-server
 * version: 1.0.0
 * 
 * Mcp 클라이언트
 * Mcp 서버
 * McpTool
 * 
 * @McpTool(name ="", description ="")
 * - AI가 사용해도 되는 도구!
 * 
 * name: AI가 확인하는 도구 이름
 * description : 이 도구가 무슨 일을 하는지 설명
 * 메서드명() : 실제로 실행되는 메서드!
 * 
 * 자연어
 * - 사람이 평소에 사용하는 말!
 * 
 * 
 * 
 * Protocol
 * - 서로 통신하기 위해 미리 정한 약속! 규칙!
 * - 컴퓨터 끼리 서로 데이터를 주고 받을 때 정해진 규칙!
 * 
 * HTTP : 웹에서 데이터를 주고 받는 규칙!
 * 
 * 
 */