package com.yonsai.Day66_20260903.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;
import com.yonsai.Day66_20260903.service.AiService;

/*
1.  AI랑 대화하기(바이브코딩)
GPT -> Model(AI의 두뇌!) ->LLM -> Prompt -> 응답

2. AI가 글을 처리하는 방법(RAG)
Token -> Context -> Temperature -> Hallucination 
 
3. 프로그램 (셀레니움,크롤링,공공데이터)
API -> API-KEY-> Request -> Response 
이미지들 다루는 방법 중에 2가지 

1. AI한테 이미지를 준다.  (MultiModal)
 - AI가 여러 종류의 정보를 같이 이해하는 것!
 - 글자만 보는게 아니라 이미지,음성,영상까지 같이 보는 AI

 문서들을 읽고 압축을 풀고 그걸 도와주는 도구 MCP
 
4. Agent - 스프링부트 프로젝트 생성!
         git 생성, git push 생성 

HTML input태그들은 기본적으로 문자만 서버로 보낼수있다. 
enctype="application/x-www-form-urlencoded" 형식 
이름=값
*/

@Controller
public class ImageAnalysisController {

  @Autowired
  private AiService service;

  @GetMapping("/imgAi")
  public String imageAi() {
    System.out.println("ImageAnalysisController - imageAi");

    return "index";
  }

  @PostMapping("/upload")
  public String upload(MultipartFile image, Model 상자) {
    System.out.println("ImageAnalysisController - upload()");
    System.out.println("이미지 파일명: " + image.getOriginalFilename());

    // 1. AIservice 이미지를 AI에게 보내서 분석해달라고 요청!
    String 결과 = service.이미지분석(image);
    // 2. 분석한 결과를 화면에 보여주기 (HTML로 보내기)
    상자.addAttribute("result", 결과);
    return "index";
  }

  @PostMapping("/vision")
  public String visionPro(MultipartFile image) {
    System.out.println("ImageAnalysisController - visionPro");

    // 1. 이미지를 구글이 이해하는 형태로 바꾸기
    // -1) 바이트 배열로 먼저 바꿈
    // -2) 구글 타입으로 변경!
    try {
      ByteString 구글이원하는이미지변경 = ByteString
          .copyFrom(image.getBytes());
      // 2. 어떤 기능을 원하는지 지정
      Image 이미지객체 = Image.newBuilder()
          .setContent(구글이원하는이미지변경)
          .build();
      // 3. 어떤 기능을 원하는지 지정
      // OCR,이미지 속 글자 읽기
      Feature 어떤기능 = Feature.newBuilder()
          .setType(Feature.Type.TEXT_DETECTION)
          .build();

      // 4. 위에 이미지 객체랑 기능이랑 묶어서 구글로 전송!
      AnnotateImageRequest 요청 = AnnotateImageRequest.newBuilder()
          .addFeatures(어떤기능)
          .setImage(이미지객체)
          .build();

      // 5. 통신도구생성!
      ImageAnnotatorClient 통신도구 = ImageAnnotatorClient.create();

      // 6. 전송!
      AnnotateImageResponse 결과 = 통신도구.batchAnnotateImages(List.of(요청))
          .getResponsesList()
          .get(0);

      // 7. 확인
      System.out.println(결과.getTextAnnotationsList()
          .get(0)
          .getDescription());
   String 추출된텍스트=결과.getTextAnnotationsList()
          .get(0)
          .getDescription();
            // Ai가 읽을 수있는 Document 문서로 변경 
     String 진짜결과 = image.getOriginalFilename()
                      + "이미지에서 다음 텍스트가 발견됨"
                      + 추출된텍스트;

      Document 문서 = new Document(진짜결과);
      //8. 벡터DB에 저장 
      service.saveToVectorStore(List.of(문서));

    } catch (IOException e) {
      e.printStackTrace();
    }
    return "index";
  }

}





/*
 * Google Vision API
 * 1. 구글 콘솔에서 등록하기 (서비스계정) - 강사 json 파일저장하기
 * 2. 구글 vision api 다운로드 pom.xml 에 라이브러리 추가!
 * 3. vision을 사용하기 위한 도구(자바 클래스!)
 * -1) ByteString
 * 이미지의 원본 바이트 데이터를 담는 자바클래스파일
 * -2) Image
 * 이 사진이 분석 대상이야! 저장하는 자바 클래스 파일
 * -3) Feature
 * 이미지에서 어떤 기능을 수행할지 저장하는 자바 클래스 파일
 * 뭘 분석하는데?요청사항뭔데?
 * 
 * 정해진 기능 목록 Feature.Type
 * TEXT_DETECTION 이미지 속 텍스트 감지 (OCR)
 * LABEL_DETECTION 사물/개념 라벨링 (예: "dog", "car")
 * FACE_DETECTION 얼굴 위치 및 표정 감지
 * LOGO_DETECTION 브랜드 로고 감지
 * LANDMARK_DETECTION 유명 랜드마크(건물 등) 감지
 * SAFE_SEARCH_DETECTION 유해 콘텐츠(성인물 등) 여부 감지
 * -4) AnnotateImageRequest
 * Image(뭘 분석할지) 와 Feature (뭘 알고 싶은지)를 하나로
 * 묶는 최종 요청 자바 클래스 파일!
 * 
 * -5) ImageAnnotatorClient
 * 실제 구글 서버와 통신하는 전용 도구!
 * 
      
 * -6) AnnotateImageResponse
 * 구글 서버가 분석을 마치고 돌려주는 값을 저장하는 자바 클래스파일
 * 
 * 실행 흐름
 * ByteString으로 사진 준비 -> Image 로 사진 저장 -> Feature로 할일
 * 선택-> Request 요청 -> Client 전송 -> Response로 결과 받기
 * 
 * 서비스Json파일을 환경변수로 설정할 때 절때 맘대로 이름 작성하면 안됨!
   꼭 구글이 정해놓은 이름으로 GOOGLE_APPLICATION_CREDENTIALS 환경변수명 세팅하기

 * 
 * 멀티모달 LLM == gpt,재미니,클로드
 * 
 * 멀티모달 LLM은 이미지 하나를 분석할 때마다 "이해하고 문장을 생성"
 * 무거운 연산 -> 비용이 엄청 크다!
 * 
 * 멀티모달LLM 이미지를 이해하려는 특성 때문에 , 숫자나 특수 문자는 가끔
 * 잘못읽거나 영수증 금액 10,000 -> 1,000 착각하는 경우나 없는 텍스트를
 * 지어내는 환각이 생길 수있음
 * 
 * 멀티모달 자유로운 문장으로 답하기 때문에 매번 출력하는 형식이 달라요
 * 그 결과가지고 처리를 해야된다 파싱하기 어려워요!
 * 속도도 이해하고 생각하고 응답하는 문장을 생성해야되기 때문에 몇 초 걸릴수
 * 있음
 * 
 * 실무에서는 구글 vision api 정확하게 이미지를 확인한다. 애매하게
 * 오는 경우는 멀티모달로 확인
 */