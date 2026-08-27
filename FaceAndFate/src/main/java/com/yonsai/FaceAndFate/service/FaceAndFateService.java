package com.yonsai.FaceAndFate.service;

import java.time.LocalDate;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.content.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yonsai.FaceAndFate.entity.SajuResult;
import com.yonsai.FaceAndFate.repository.SajuResultRepository;

@Service
public class FaceAndFateService {

  // 디비담당직원 배정!
  @Autowired
  SajuResultRepository 디비담당자;

  // AI 연결하고 AI 질문보내고 받는 클래스
  @Autowired
  private ChatModel gpt정보;

  public void AI질문답변처리(MultipartFile 이미지, String 생년월일) {

    // AI채팅창 만들기
    ChatClient AI채팅창 = ChatClient.builder(gpt정보).build();

    // 이미지랑 메시지 보내기
    try {
      byte[] 이미지데이터 = 이미지.getBytes();

      String 답변 = AI채팅창
          .prompt()
          .user(user -> user
              .text("""
                  관상과 사주를 재미로 분석해주세요.

                  생년월일: %s

                  얼굴 사진을 참고해서
                  성격, 대인관계, 직업 성향을
                  재미있게 설명해주세요(400자).
                  """.formatted(생년월일))

              // ⭐ 이미지 전달
              .media(
                  Media.builder()
                      .mimeType( // 이 이미지가 무슨 파일인지 알려주는것!
                          MimeTypeUtils.parseMimeType(
                              이미지.getContentType()))
                      .data(이미지데이터)
                      .build()))
          .call()
          .content();

      System.out.println(답변);

      // 직원 불러서 디비에 저장시키기!
      SajuResult 사주한건 = new SajuResult();

      // 문자 -> LocalDate타입으로 변경
      LocalDate birth = LocalDate.parse(생년월일);

      사주한건.setBirthDate(birth);
      사주한건.setAiResult(답변);

      // DB담당직원 부르기!
      디비담당자.save(사주한건);

    } catch (Exception e) {
    }

  }

  // 오늘의 운세를 처리하는 함수!
  public String 오늘의운세(LocalDate 생년월일) {
    // 1. AI정보 설정
    ChatClient AI채팅창 = ChatClient
        .builder(gpt정보)
        .build();
    // 2. 생년월일,오늘날짜 입력받기
    // LocalDate.now() 현재 날짜 가져와!
    // toString() 문자로 변경해!
    String 오늘날짜 = LocalDate.now().toString();

    // 3. AI보내기 그리고 답변받기
    String 답변 = AI채팅창
        .prompt()
        .user(u -> u.text("""
            생년월일: %s
            오늘 날짜: %s

            위 생년월일을 가진 사람의 '오늘 하루' 운세를
            재미로 짧게 3줄로 알려줘.(만세력 기준)
            """.formatted(생년월일, 오늘날짜)))
        .call()
        .content();

    System.out.println("오늘의 운세");
    System.out.println(답변);

    return 답변;
  }

}
/*
 * 기본 문자지만 이미지와 문자가 들어갈 때는
 * user 사용자 채팅이 어떻게 동작할지 코드를 작성한다.
 * 
 * .user(user -> user
 * .text() 넣고
 * .media() image 넣고
 * )
 * 
 * Entity 폴더
 * - 저장할 데이터 타입을 저장한다.
 * 관상 (생년월일,관상 결과)
 * 
 * 전체 흐름 비유해서 정리
 * Controller (주문 접수처)
 * ↓ "이 사주 알려줘!"
 * Service (주방장/처리담당)
 * ↓ "gpt 질문을 보내고 답변 받는 처리!"
 * "직원아! 이거 창고 넣어줘! AI답변 + 생년월일"
 * Repository (창고 직원)
 * ↓ save(SajuResult)
 * DB(진짜 창고 Mysql)
 * 
 */