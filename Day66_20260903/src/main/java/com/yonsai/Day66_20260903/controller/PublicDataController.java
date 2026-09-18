package com.yonsai.Day66_20260903.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

@Controller
public class PublicDataController {

    // 실무에서 기준이 되거나 변경되지 않는 데이터들
    // 기본적으로 대문자로 작성한다.
    private String MOVIE_API_KEY = "key";

    // 공공데이터 약국 API
    private String PUBLIC_URL = "https://apis.data.go.kr/1741000/pharmacies/info?";
    private String PUBLIC_API_KEY = "key";

    @GetMapping("/ph")
    public String pharmaciesData() {
        System.out.println("PublicDataController - pharmaciesData()");

        RestClient 통신도구 = RestClient.create();

        String url = PUBLIC_URL
                + "serviceKey="+PUBLIC_API_KEY
                + "&pageNo=1"
                + "&numOfRows=10"
                + "&returnType=json";

        // 2. 전송
        String 결과 = 통신도구.get()
                .uri(url)
                .retrieve()
                .body(String.class);
        System.out.println(결과);

        return "index";
    }

    @GetMapping("/movie")
    public String movieData(Model 상자) {
        System.out.println("PublicDataController - movieData()");

        return "index";
    }

}
/*
 * // 1. API - 프로그램들끼리 데이터를 주고 받는 규칙!
 * // API-KEY 사용하는지 안하는지? 어떤 API들을 사용할지 검색!
 * 
 * // 최근 개봉한 영화목록을 보여주는 사이트를 만들겠다.
 * 
 * 통신 - 서로 데이터를 주고 받는걸
 * 대상 - 컴퓨터(서버)랑 컴퓨터(서버)야?(REST API) 핸드폰하고 컴퓨터(서버)?
 * 브라우저랑 서버랑 ?
 * 
 * 서버랑 서버끼리 통신하기 위한 도구!
 * WebClient, RestClient(최신도구!)
 * 
 * 
 * 
 * // 실무에서 기준이 되거나 변경되지 않는 데이터들
 * // 기본적으로 대문자로 작성한다.
 * private String MOVIE_API_KEY = "key";
 * 
 * @GetMapping("/movie")
 * public String movieData(Model 상자) {
 * System.out.println("PublicDataController - movieData()");
 * 
 * // 1. 웹통신하는 도구생성!
 * RestClient 통신도구 = RestClient.create();
 * 
 * // 2. 전송하기!
 * Map 결과 = 통신도구.get()
 * .uri(
 * "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=key&targetDt=20260830")
 * .retrieve() // 데이터 가져오기!(전송!)
 * .body(Map.class);
 * 
 * // 위에 String으로 받을 결과를 Map형식으로 변경
 * 
 * // HTML로 보내서 순위,영화이름,개봉일,관객수! 각각
 * // 화면에 출력하기
 * 
 * // 3. 확인
 * // System.out.println(결과);
 * // System.out.println(결과.get("boxOfficeResult"));
 * 
 * Object 결과2 = 결과.get("boxOfficeResult");
 * // key와 value뽑는 Map타입으로 변경
 * Map<String, Object> 결과3 = (Map<String, Object>) 결과2;
 * 
 * // 결과3에서 dailyBoxOfficeList 꺼내기!
 * List<Map<String, Object>> 결과4 = (List<Map<String, Object>>) 결과3
 * .get("dailyBoxOfficeList");
 * 
 * // Map타입을 출력할 때만 문자로 바꾸겠다!
 * // System.out.println(결과4.toString());
 * 
 * // 영화 제목만 뽑기
 * for (Map<String, Object> 영화한개 : 결과4) {
 * System.out.println(영화한개.get("movieNm"));
 * }
 * return "index";
 * }
 */