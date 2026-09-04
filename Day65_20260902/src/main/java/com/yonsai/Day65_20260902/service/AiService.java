package com.yonsai.Day65_20260902.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    // AI가 준비하는 공간!
    // 1. ai들의 정보!
    // 2. ai들에게 보낼 메시지를 작성!

    // AI정보를 저장하는 도구!(클래스파일)
    @Autowired
    ChatModel ai정보저장;
    // 실제 채팅창을 만들어야된다.(채팅창만드는 클래스파일)
    ChatClient 채팅창;

    // 임베딩모델 도구(클래스파일 가져오기)
    // 문장이나 단어를 벡터로 바꿔주는 모델
    @Autowired
    // @Qualifier("embeddingModel")
    EmbeddingModel 임베딩모델;

    // 실제 문장 -> 숫자배열로 바꿔주는 동작!
    // float[] 임베딩시 벡터 타입
    // embed(문서) 실제 계산하는 부분!
    public float[] 숫자배열로변환중(String 문서) {
        return 임베딩모델.embed(문서);
    }

    List<Message> 대화목록 = new ArrayList<>();

    // AI질문하는 함수
    public String 질문하기(String 질문) {
        // 클라이언트가 질문한 내용을 AI로 보내기

        // 1. 채팅창 설정
        채팅창 = ChatClient
                .builder(ai정보저장)
                .build();
        Message 질문메시지 = UserMessage
                .builder()
                .text(질문)
                .build();
        대화목록.add(질문메시지);

        // 2. 질문과 context 입력
        String 답변 = 채팅창.prompt()
                .messages(대화목록)
                .user(질문)
                .call()
                .content();
        System.out.println(답변);
        Message 답메시지 = new AssistantMessage(답변);

        대화목록.add(답메시지);

        return 답변;

        // 3. 보내고 받아서 출력파서
    }

}

// LLM이 똑똑하긴 하지만 최신 정보나 회사 내부 문서들
// 몰라요! 그럼 llm들은 거짓말(환각(Hallucination))치는
// 문제들이 나온다.
// 그래서 나온 방식이 바로 RAG

// RAG = 필요한 자료를 먼저 찾아본다.->AI에게 보내준다
// -> 답하게 하는 방식

// 토큰 (레고조각) - 작은 단위로 쪼갬
// 임베딩 - 문자를 숫자배열(벡터)로 변환 (채점표!)
// 코사인유사도 - 벡터끼리 방향을 비교해서 일치율 계산

// 내부 자료들을 AI한테 정보제공!
// 조금 더 정확성을 올리는 작업 (RAG)

// 임베딩된 벡터들을 따로 DB에 저장하나요?
// - 벡터DB
// Pinecone - 클라우드형 벡터 DB
// Chroma - 로컬 환경
// PGVecotr - PostgreSql 벡터 검색 ,저장

// AI만 사용
// 질문하면 AI가 자기 학습 내용으로 답변
// RAG 이해
// 내 문서를 AI가 참고하게 만들고 싶음
// 그럼 필요한것
// 문서를 임베딩(숫자배열)
// 벡터DB에 저장 기억
// 질문이 왔을 때 비슷한 문서 검색
// AI 전달

// 파인튜닝
// - 이미 학습된 AI모델을 내 목적에 맞게 추가 학습시키는 것!

// RAG 전체 그림
// 1. 도큐먼트 로드
// 2. 텍스트 분할
// 3. 임베딩
// 4. 백터스토어 저장
// 5. 검색기
// 6. 프롬프트
// 7. LLM모델 호출
// 8. 체인/출력파서

// 프롬프트
// - 검색된 문서를 바탕으로 언어 모델이 사용할 질문이나
// 명령을 생성하는 과정

// 문맥(Context) 설정
// - 정확한 답변을 하기 위해서 제공된 정보를 보낸다.

// 정보 통합
// - 여러 문서 검색된 비슷한 문서들을 어떻게 담아서
// 통합해서 효율적으로 활용할 수있는 형식 정리하는 것!

// RAG에 성능 최적화하고 품질을 높이는 방법!

// RAG 프롬프트 구조
// 지시사항 - AI에게 역할과 규칙을 알려주는 부분
// 질문 - 사용자 입력한 질문
// 문맥 - 검색된 정보

// 출력파서
// - AI응답을 사람이 읽기 좋게 받지 말고 , 내가 만드는
// 프로그램이 바로 쓸 수 있는 형태로 받자!

// 스트리밍 지원
// - 실시간으로 채팅이 작성되는걸 지원한다.

// 구조화된 데이터로 쉽게 변환할 수있고 프로그램이 바로
// 사용할 수있기 때문에!

// 1. 설정
// 라이브러리를 다운로드
// openAI + application.properties 설정

// 도큐먼트 로더
//  - 먼저 어떤 종류의 데이터가 필요한지,
//    데이터를 어디서 어떻게 수집할지 결정!
//  - 데이터 소스 선택(출저,신뢰있는 데이터)
//  - 데이터수집 
//  - 데이터 필터링 및 전처리 
//  - 데이터 로드! ( 문서 분할 )