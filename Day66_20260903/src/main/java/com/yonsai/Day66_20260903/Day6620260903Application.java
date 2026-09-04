package com.yonsai.Day66_20260903;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day6620260903Application {

	public static void main(String[] args) {
		SpringApplication.run(Day6620260903Application.class, args);
	}

}
/*
# 현재 openAI  GPT 
#  웹사이트 들어가서 GPT를 이용하면 이전 내용을 기억!
# 지금처럼 API KEY 외부에서 접근 하면 이전 내용을 기억 안함!세팅
#  이전대화기억 (Chatmemory)

# 내가 가진 내부 문서들을 먼저 확인 후 RAG
# 임베딩 - 문자를 토큰 단위 쪼갠 후 점수표(숫자 배열 - 벡터)
# 코사인유사도 - 기존 AI가 가지고 있는 문서랑 현재 문서랑 비교해서 일치율 (실수)
#             유사도가 높다 1 0 -1

# 문서를 읽어드리면 해야되는 일들!
# 토큰(Token) - AI가 글을 세는 단위!
# 청크(Chunk) - 문서를 잘라낸 하나의 조각(잘라진 문서 한 조각)
# 컨텍스트 윈도우 (Context Window) - AI모델이 한 번에 처리할 수있는 (입력)토큰 수!
# KV 캐시(Key-Value Cache) - GPU메모리에 쌓여서 처리된다. 
# 오버랩(OverLap) - 문서와 문서를 잘라서 경계에서 문장이 끊기게 하지 않는 기법!

# DocumentReader 
#  읽어온 문서들을 AI모델이 처리하기 좋은 크기로 나누는(청크)잘라주는 도구!

# spring ai
# 문서읽기 -> TextSplitter로 자르기 -> 임베딩 -> Vetor DB 저장 

# 컨텍스트 윈도우 단점
#  - AI가 답을 하는 순간에만 GPU메모리에 잠깐 존재했다가 답변이 오면 사라지는 
#    임시 작업 공간!

# 벡터DB 장점
#  - 서버 디스크/메모리에 계속 저장 
#  - 영구 보관 , 검색 기능!

# 컨텍스트 윈도우는 모델마다 고정되어있다 # GPU 메모리  VRAM 
# GPT-3.5  약 16000토큰
# GPT-4o/GPT-4o mini    약 128000토큰 
# Claude  모델별로 다름(수십만 토큰)

# 어떤 프로그램을 만들때 어떤 AI를 가져와서 사용하는게 효과적이냐!?
# 비용을 줄일 수있는 방법?

# 문서를 자르기 위해서 필요한 것!
# 1. 라이브러리 가져오기


# chat 메시지
현재 프로젝트 폴더 안에 자바 폴더 생성해줘
controller , service 폴더 생성 하고 
controller 폴더 안에 파일명 : HomeController 
service 폴더 안에 파일명: PdfService 

HomeController 파일 안에  url "/" GetMapping 함수명:index

resources 폴더 안에 templates 안에 index.html  파일 생성후 
h2태그 : PDF 문서 자르기 만 넣어줘

나머지 폴더나 파일은 건들지마!
*/ 