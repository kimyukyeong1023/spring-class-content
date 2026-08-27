package com.yonsai.openai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenaiApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenaiApplication.class, args);
  }
}

/*
 * 토큰나이저
 * - AI가 문장을 잘게 쪼개서 "토큰"이라는 단위로 바꾸는 프로그램
 * - 문자를 입력했을 때 토큰 개수를 알려준다.
 * 
 * 토큰으로 바꾸지?
 * - LLM은(gpt,재미니,클로드) 우리가 쓰는 문장 자체로 그대로
 * 처리를 하는게 아니라 토큰으로 변환해서 처리한다.
 * 
 * 토큰 쪼개는 기준
 * - 자주 붙어나오는 조합 통계적으로 찾아서 잘름
 * 
 * 제일 작은 조각으로 쪼갠 상태 (BPE)
 * hello -> h, e , l , l, o (낱개 또는 바이트 단위)
 * 
 * 학습 데이터 전체를 보면서 가장 자주 붙어 나오는 쌍을 하나로 합침
 * h, ell, o -> 단어로 연결한 다음 통쨰 토큰 하나가 됨
 * 수십만번 반복해서 봤더니 hello 나오더라 토큰 사전 공간에 저장
 * hello -> 1토큰
 * 
 * 재미니
 * Gemini 2.5 Flash-Lite 비용 부과 100만토큰 1,000,000 당 $0.15
 * - 1000글자(A4 한페이지분량) -> 기본 토큰 571토큰 -> 0.1원
 * 
 * 한도제한을 꼭! 걸어주셔야된다.
 * 
  Api-key 
   - 서비스 이용 자격을 확인하는 비밀번호!
  openAi 
   - 서버 (AI에게 요청을 보내고 답변만 받아오는 방식)

  토큰 단위 
   - 1token (최소단위)  영어 - 약 4글자, 한글 2글자
   - 1k tokens
   - 1m tokens

  질문(입력)토큰  - input token (내가 보내는 프롬프트)
  답변(출력)토큰  - output token (모델이 생성하는 답변 입력보다는 비싸다!)
   

 */