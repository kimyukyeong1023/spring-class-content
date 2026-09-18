package com.yonsai.aws.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

import tools.jackson.dataformat.xml.XmlMapper;

@Controller
public class XmlController {

  // yaml 파일에 있는 API-KEY 자바 변수에 넣어야된다.
  @Value("${public-data.service-key}")
  private String serviceKey;

  @GetMapping("/")
  public String loan() {
    System.out.println("XmlController - loan()");

    // 1. 도구들 가져오기!
    RestClient Http통신도구 = RestClient.create();
    XmlMapper xml을읽는도구 = XmlMapper.builder().build();

    // 2. 먼저 데이터 가져오기 (통신)
    String url = "http://apis.data.go.kr/1160100/service/GetSmallLoanFinanceInstituteInfoService/getOrdinaryFinanceInfo?"
        + "serviceKey=" + serviceKey
        + "&pageNo=1"
        + "&numOfRows=10";

    // 3. 보내서 응답받기(XML)
    String xml = Http통신도구.get()
        .uri(URI.create(url)) // serivceKey 이중 인코딩X
        .retrieve()
        .body(String.class);

    // 4. 확인
    // System.out.println(xml);

    // 5. XML로 꺼내기
    var 대출상품들 = xml을읽는도구.readTree(xml);

    // 6. items가져오기
    var body = 대출상품들.get("body");

    // System.out.println(body);
    var items = body.get("items");
    var itemsList = items.get("item");

    for (var item : itemsList) {
      System.out.println(item.get("finPrdNm"));
      System.out.println();
      // System.err.println(item.get("finPrdNm").asString());
    }

    return "xml";
  }

  @GetMapping("/xml")
  public String xmlPage() {
    System.out.println("XmlController - xmlPage()");

    String xml = "<hotel><name>서희호텔</name><address>서울 강남구</address></hotel>";

    // 1.XML도구
    // XmlMapper - 데이터를 자바에서 다루도록 변환하는 도구!
    XmlMapper xml을읽는도구 = XmlMapper.builder().build();

    // 2. 꺼내기
    // var - 오른쪽에 값을 보고 컴파일러가 자료형을 정해주는 문법
    // -1) 호텔정보
    var 호텔정보 = xml을읽는도구.readTree(xml);

    // 호텔명,주소 한꺼번에 꺼낸다.
    String 호텔명 = 호텔정보.get("name").asString();
    String 주소 = 호텔정보.get("address").asString();

    // 확인
    System.out.println("호텔명:" + 호텔명);
    System.out.println("주소:" + 주소);

    return "xml";
  }
}
/*
 * XML?
 * - 데이터에 태그를 붙여서 표현하는 방식!
 * - <이름표>내용</이름표>
 * 
 * <hotel>
 * <name>서희호텔</name>
 * <address>서울 강남구</address>
 * </hotel>
 * 
 * {
 * name: "서희호텔",
 * address: "서울 강남구"
 * }
 * 
 * 단점
 * - 태그들어가서 글자수가 많아진다.
 * - 데이터 용량이 커지기 쉬움
 * - 구조가 복잡해진다.
 * - 자바스크립트에서 처리가 번거롭다.
 * 
 * XML를 쓰는 이유?
 * - 기존 시스템과 잘 연결되고 복잡한 문서 규칙을 표현하기
 * 좋아서 아직까지 사용!
 * - 실무에서는 Mybatis 데이터베이스랑 스프링부트랑 연결해서
 * 저장하는 방식!
 * 
 * 스프링부트는 JSON 형식 자동으로 파싱하는 도구 들어있다.
 * XML을 파싱하는 도구는 들어있지 않다.
 * XML 도구를 다운로드 해야된다.
 * 
 * 파싱(Parsing)
 * - 파일이든 API를 호출해서 데이터를 가져오던 읽어서
 * 필요한 값만 꺼낼 수있도록 하는 것!
 * 
 * XML 다운로드할때 라이브러리 버전!
 * - 스프링부트 4.X
 * implementation 'tools.jackson.dataformat:jackson-dataformat-xml'
 * 
 * 
 * String 호텔명 = xml을읽는도구
 * .readTree(xml) // xml파일 읽기
 * .get("name") // 태그명
 * .asString(); // 문자타입으로 변경
 * // 3. 확인
 * System.out.println("결과: " + 호텔명);
 * 
 * // address 태그 안에 내용을 파싱해서 출력하기
 * String 주소 = xml을읽는도구
 * .readTree(xml) // xml파일 읽기
 * .get("address") // 태그명
 * .asString(); // 문자타입으로 변경
 * // 3. 확인
 * System.out.println("결과: " + 주소);
 */