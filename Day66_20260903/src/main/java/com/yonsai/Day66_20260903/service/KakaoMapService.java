package com.yonsai.Day66_20260903.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.yonsai.Day66_20260903.dto.PharmacieDto;

import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class KakaoMapService {

    // 1. 공공데이터 가져오기 (API-KEY, BaseUel)
    // -DB처럼 원하는 값만 select해서 가져올 수 없다.
    private String PUBLIC_URL = "https://apis.data.go.kr/1741000/pharmacies/info";
    private String PUBLIC_API_KEY = "key";

    @Autowired 
    private AiService aiService;

    // 2. 실제 일처리하는 함수
    public List<PharmacieDto> kakaoData() {
        System.out.println("KakaoMapService - kakaoData()");

        // 3. 통신도구 생성
        RestClient 통신도구 = RestClient.create();

        // 4.URL 만들기
        String url = PUBLIC_URL
                + "?serviceKey=" + PUBLIC_API_KEY
                + "&pageNo=1"
                + "&numOfRows=10"
                + "&returnType=json";

        // 5. 전송
        // 통신도구를 이용해서 조회해라! -> 공공데이터에서 데이터 가져와!
        // -> 가져오면 자바가 쓸 수 있게 String문자로 바꿔라!
        Map 결과 = 통신도구.get()
                .uri(url)
                .retrieve()
                .body(Map.class); // key,value을꺼내는 Map형식으로 바뀜
        // .body(String.class);

        // 6. 공공데이터 응답에서 약국 목록 꺼내기
        Map response = (Map) 결과.get("response");
        Map body = (Map) response.get("body");
        Map items = (Map) body.get("items");
        Object item = items.get("item");

        List<Map<String, Object>> 약국목록 = new ArrayList<>();
        if (item instanceof List) {
            약국목록 = (List<Map<String, Object>>) item;
        } else if (item instanceof Map) {
            약국목록.add((Map<String, Object>) item);
        }

        // 7. 필요한 정보만 DTO에 저장
        List<PharmacieDto> 약국정보들 = new ArrayList<>();

        for (Map<String, Object> 약국 : 약국목록) {

            PharmacieDto 약국정보 = new PharmacieDto();
            약국정보.set이름((String) 약국.get("BPLC_NM"));
            약국정보.set영업중("영업중".equals(약국.get("DTL_SALS_STTS_NM")));
            약국정보.set주소((String) 약국.get("ROAD_NM_ADDR"));
            약국정보.set전화번호((String) 약국.get("TELNO"));

            약국정보들.add(약국정보);
        }

        System.out.println(약국정보들);

        // 8. Rag에 저장
        List<Document> 변경된문서들 =toDocuments(약국정보들);

        // 9.벡터 저장
        aiService.saveToVectorStore(변경된문서들);

        return 약국정보들;
    }

    // 내가 가지고 있는 약국정보들을 AI문서인 Document 변경해서 저장
    public List<Document> toDocuments(List<PharmacieDto> 약국정보들) {

        return 약국정보들.stream()
                .map(약국 -> {
                    String text = 약국.get이름() + "은(는) " + 약국.get주소() + "에 위치하며, "
                            + (약국.get전화번호() != null && !약국.get전화번호().isEmpty()
                                    ? "전화번호는 " + 약국.get전화번호() + "이고, "
                                    : "전화번호 정보는 없고, ")
                            + (약국.get영업중() ? "현재 영업중입니다." : "현재 영업종료 상태입니다.");

                    return new Document(text);
                })
                .collect(Collectors.toList());

                
    }

    public String 질문보내기(String q) {
        return aiService.askAi(q);
    }

}