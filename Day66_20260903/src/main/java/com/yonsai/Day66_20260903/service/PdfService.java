package com.yonsai.Day66_20260903.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// PDF문서를 읽어서 처리하는 작업 공간
// AI에게 질문을 보내고 받기 위해서 준비하는 공간! Service
// List      java.utill  여러개 저장!
// Document  org.springframework.ai.document 
//            AI 넘길 글 한덩이! "병원 예약은 홈페이지에서 가능합니다." 
//            "진료시간은 오전 9시부터 오후 6시까지입니다."

//  Document Spring AI에서 글을 담는 객체()
//  Chunk 긴 글을 잘라놓은 한 조각

// Document == Chunk 하나!
@Service
public class PdfService {

  @Autowired
  EmbeddingModel embeddingModel;

  // 전체 모든 함수가 저장소를 쓸 수 있도록 전역변수
  SimpleVectorStore 저장소;

  public List<Document> pdf자르기() {

    // ------------------------------------------------
    // 1. 파일 읽기(업로드,파일열기)
    // classpath:/ = resources 폴더부터 찾아가!
    PagePdfDocumentReader 파일가져오기 = new PagePdfDocumentReader("classpath:/스타트업.pdf");

    // 2. 파일을 분리시킨다 페이지별로! 중요한 내용과 부가적인 내용들 자동을
    // 분리해서 Document 타입으로 가져온다. (이 개념 도큐먼트로드)
    // 정리해서 가져오기!
    List<Document> 원본문서들 = 파일가져오기.get();

    // 3. AI 보내기 위해서 토큰 처리하는 옵션들!
    // 기본적으로 청크 500
    // 청크 사이즈 지정하는 이유 - 검색 정확도와 문맥 유지의 균형 맞추기
    // 위해서!
    // TokenTextSplitter 문서를 몇 토큰씩 나눌지 정해서 잘라주는 가위!(도구)
    // 500토큰 정도씩 자르는 도구를 만들어라!

    // 포폴 준비시 RAG 제일 중요한건 어떻게 토큰을 결정!?
    // 테스트하는 이미지들 찍어야된다. 거기의 문제점,왜 이 토큰 사이즈를 결정
    // 했는지 이게 중요!

    TokenTextSplitter 토큰가위 = TokenTextSplitter.builder()
        .withChunkSize(300)
        .build();

    // apply(원본문서들)
    // 토큰가위로 원본 문서들을 잘라라!
    List<Document> 분할된문서들 = 토큰가위.apply(원본문서들);

    // 확인
    for (Document 청크 : 분할된문서들) {
      System.out.println(청크.getText());
      System.out.println();
      System.out.println("-------------------------------");
    }
    // 저장
    벡터저장(분할된문서들);

    return null;
  }

  // 백터DB저장!
  // 가장 간단하게 메모리용 벡터 저장소를 하나 만들어서 문서에
  // 넣는 것!
  // SimpleVectorStore
  // - 숫자를 저장하는 간단한 저장소
  public void 벡터저장(List<Document> 청크단위나눠진파일들) {

    // 1. 저장할곳 생성
    저장소 = SimpleVectorStore
        .builder(embeddingModel)
        .build();

    // 2. add() 추가
    저장소.add(청크단위나눠진파일들);

    // 3. 확인
    System.out.println("벡터저장 완료! "
        + 청크단위나눠진파일들.size()
        + "개 청크");

    // 4.저장확인메서드실행
    저장확인("스타트업이 뭐야?");

  }

  // 저장 후 확인용 검색 함수를 추가
  public void 저장확인(String 질문) {
    // 질문과 비슷한 내용을 벡터 저장소에서 찾아줘!
    List<Document> 결과 = 저장소.similaritySearch(질문);

    System.out.println();
    System.out.println("*****************************");

    for (Document 문서한개 : 결과) {
      System.out.println(문서한개.getText());
    }

    System.out.println("*****************************");
    System.out.println();
  }

  public void 실무에서사용하는추가설정() {
    TokenTextSplitter.builder()
        .withChunkSize(500)
        // 청크가 너무 짧지 않게 최소 글자수를 지정!
        // 문장이 자연스럽게 끝나는 지점(마침표,쉼표)
        .withMinChunkSizeChars(50)

        // 임베딩 할때 너무 짧은 청크는 제외!
        // 공백 몇개, 페이지번호, 목차의 짧은 제목한줄
        // 5글자 미만은 청크에서 아예 제외하자!
        .withMinChunkLengthToEmbed(5)

        // 여러개의 문서들을 한꺼번에 읽거나 파일들의 크기가 커지면
        // 청크가 기하급수적으로 늘어난다. 문제는 메모리를 싹다 잡아먹는다.
        // 안전장치
        .withMaxNumChunks(10000)

        // PDF,텍스트들은 줄바꿈(\n)
        // true 줄바꿈 유지! , false 한줄뭉침
        .withKeepSeparator(true)

        // 위에 설정을 기준으로 가위를 만든다. (객체)
        .build();

    SimpleVectorStore m;

  }
}

/*
 * Chunk는 토큰 비용아끼기 위해서 사용!(정확도)
 * 1. 사내 문서 챗봇 (연차신청,출근 사내 메뉴얼 )
 * 2. 고객 상담 챗봇 (F&A , 제품메뉴얼)
 * 3. 코드베이스 QA
 * - 함수나 클래스 끊어지지 않게 청크 사이즈를 조절 중요!
 * 4. 법률/ 의료 문서 검색
 * 5. 긴 대화 이력 관리
 * 사용자와 AI랑 30분 넘게 대화를 이어갈때
 */

// PDF 파일
// ↓
// PagePdfDocumentReader
// ↓
// .get() spring ai가 Document 여러개로 바꾼다.
// ↓
// 원본문서들 저장한다.

// -----------------------------------------------------
/*
 * 파이프라인 실행
 * 
 * ① 원본문서들(List<Document>)을 하나씩 순회
 * ↓
 * ② 각 Document의 doc.getText()로 텍스트만 꺼냄
 * ↓
 * ③ 그 텍스트를 위 옵션들(chunkSize, minChunkSizeChars 등) 기준으로 분할
 * → 여러 개의 텍스트 조각(List<String>)이 나옴
 * ↓
 * ④ 각 텍스트 조각마다 새로운 Document 객체를 생성
 * ↓
 * ⑤ 이때 원본 Document가 가지고 있던 metadata
 * (예: 페이지 번호 "page_number: 3")를
 * 새로 만든 각 청크 Document에도 그대로 복사해서 넣음
 * ↓
 * ⑥ 이렇게 만들어진 모든 청크 Document들을
 * 하나의 List<Document>로 합쳐서 반환
 * 
 * 
 * 
 * 
 * 
 */