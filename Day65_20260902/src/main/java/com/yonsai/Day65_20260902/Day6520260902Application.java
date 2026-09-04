package com.yonsai.Day65_20260902;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yonsai.Day65_20260902.service.AiService;

@SpringBootApplication
public class Day6520260902Application {

  public static void main(String[] args) {
    // Spring Boot 애플리케이션 실행
    SpringApplication.run(Day6520260902Application.class, args);
  }

  public String 텍스트정제(String 원문) {

    return 원문
        // 탭, carriage return 등을 일반 공백으로
        .replaceAll("[\\t\\r\\f]+", " ")

        // 연속된 공백 여러 개 → 공백 하나
        .replaceAll(" {2,}", " ")

        // 줄 시작/끝에 붙은 불필요한 공백 제거
        .replaceAll(" *\\n *", "\n")

        // 빈 줄이 여러 개면 최대 한 줄 정도만 남김
        .replaceAll("\\n{3,}", "\n\n")

        // 문자열 앞뒤 공백 제거
        .trim();
  }

  // Spring Boot가 시작될 때 한 번 실행되는 테스트 코드
  @Bean
  public CommandLineRunner testRunner(AiService aiService) {

    return args -> {

      // ========================================
      // 1. PDF 읽기
      // ========================================

      // PDF 파일을 읽는 Reader 객체 생성
      PagePdfDocumentReader reader = new PagePdfDocumentReader(
          "classpath:/머신러닝_커리큘럼.pdf");

      // reader가 PDF의 텍스트를 추출하고
      // Spring AI의 Document 객체들로 만들어 반환
      //
      // Document에는
      // - 실제 텍스트
      // - 파일명
      // - 페이지 번호 등의 metadata
      // 가 들어갈 수 있음
      List<Document> 문서들 = reader.get();

      // ========================================
      // 2. 읽어온 Document 확인
      // ========================================

      System.out.println("=== PDF 읽기 결과 ===");

      // 문서들.forEach(doc -> {
      // System.out.println(doc.getFormattedContent());
      // });
      String 원문 = 문서들.get(0).getText();

      String 정제된문서 = 텍스트정제(원문);

      System.out.println(정제된문서);

      // ========================================
      // 3. 임베딩 테스트
      // ========================================

      System.out.println("=== 임베딩 테스트 시작 ===");

      Document 문서 = 문서들.get(0);

      float[] 결과 = aiService.숫자배열로변환중(
          문서.getText());

      System.out.println("결과 길이 : " + 결과.length);

      System.out.println(
          "결과 값 : " +
              java.util.Arrays.toString(결과));

      System.out.println("=== 임베딩 테스트 끝 ===");
    };
  }
}

/*
 * // 스프링부트에서 서버가 켜질때 딱 한번만 테스트용도
 * 
 * @Bean
 * public CommandLineRunner testRunner(AiService aiService) {
 * return args -> {
 * System.out.println("=== 질문하기 테스트 시작 ===");
 * float[] 결과 = aiService.숫자배열로변환중("스프링부트라는건 뭐더라? 디게 어려웠는데?");
 * System.out.println("결과 길이:" + 결과.length);
 * System.out.println("결과 값:" + java.util.Arrays.toString(결과));
 * 
 * System.out.println("=== 질문하기 테스트 끝 ===");
 * };
 * }
 * 
 * 
 * 
 * 
 */