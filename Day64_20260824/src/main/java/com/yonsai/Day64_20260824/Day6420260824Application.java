package com.yonsai.Day64_20260824;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day6420260824Application {

  public static void main(String[] args) {
    SpringApplication.run(Day6420260824Application.class, args);
  }
}

/*
 * AI(Artifical Intelligence)
 * - 사람이 만든 지능(인공지능)
 * - 컴퓨터가 사람 처럼 생각하고 판단하는 기술!
 * - 엄청난 양의 데이터를 학습한 컴퓨터 프로그램!
 * 
 * LLM(Large Language Model)
 * - 엄청나게 많은 글을 학습해서 사람의 언어를 이해하고
 * 문장을 만들어내는 AI
 * 
 * Open AI - gpt
 * Anthropic - Claude
 * Google - gemini
 * 
 * 토큰(Token)
 * - LLM이 텍스트를 처리하는 최소 단위
 * - 최소한의 글자 조각!
 * 
 * 안녕하세요 -> 5토큰
 * hello world -> 2토큰
 * 
 * AI 자주 쓰는 단어 조각 사전 만들어놓는다.
 * 영어는 학습 데이터가 압도적으로 높다! 그래서 Hello 통째로 등록!
 * 한국어는 학습 데이터가 적고,글자 조합도 훨씬 많아서 안녕하세요 통째로
 * 등록을 못한다 그래서 한글자씩 쪼개서 저장
 * 그래서 한글로 입력하면 앞도적으로 토큰 비용이 많이 나온다.
 * 
 * 대체적으로 한번 AI를 호출하는 비용 0.15원 , 1.5원
 * 
 * 프롬프트(Prompt)
 * - AI에게 보내는 질문 또는 명령어
 * 
 * System Prompt - AI의 역할/ 성격 지정
 * User Prompt - 사용자가 실제로 입력하는 질문
 * Assiatant Prompt - AI의 이전 대화 유지!
 * 
 * 
 * Spring AI - 스프링부트에서 AI모델을 쉽게 사용할 수있도록 도와주는
 * 공식 프레임워크!
 * 
 */