package com.yonsai.Day66_20260903.controller;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.jsoup.JsoupDocumentReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yonsai.Day66_20260903.service.AiService;

import io.github.bonigarcia.wdm.WebDriverManager;

@Controller
public class CrawingController {

    @Autowired
    AiService aiService;

    @GetMapping("/craw")
    public String craw() {
        // 로그 항상찍기!
        System.out.println("CrawingController - craw()");

        // 1. url 가져오기
        String[] urls = {
                "https://www.ediya.com/contents/bakery.html",
                "https://www.ediya.com/contents/drink.html",
                "https://www.ediya.com/contents/product.html"
        };

        // 결과를 담아둘 리스트!
        List<Document> 전체문서들 = new ArrayList<>();

        // 2. url 접속해서 텍스트 가져오기
        for (String 하나의주소 : urls) {
            JsoupDocumentReader 크롤러 = new JsoupDocumentReader(하나의주소);
            전체문서들.addAll(크롤러.get());
        }

        // 3. 출력
        for (Document 문서 : 전체문서들) {
            System.out.println(문서.getText() + "\n\n");
            System.out.println();
            System.out.println("-----------------------------");
        }

        // 4. 벡터DB 저장
        aiService.saveToVectorStore(전체문서들);

        // 5. AI질문하기
        String 결과 = aiService.askAi("스타벅스 아메리카노 메뉴가 있니?");
        System.out.println("나온 결과!: " + 결과);
        // 페이지이동!
        return "index";
    }

    // 자동화 기초
    // 1. 브라우저 열기
    @GetMapping("/sel")
    public String 자동화화면열기() {
        System.out.println("CrawingController - 자동화화면열기()");

        // 브라우저 조종할 수있게 준비!
        // 내 크롬 버전에 맞는 크롬 드라이버를 자동 준비!
        WebDriverManager.chromedriver().setup();

        // 화면을 자동으로 열기(실행)
        ChromeDriver driver = new ChromeDriver();

        // 접속할 사이트 주소!
        driver.get("https://www.naver.com");

        // 원하는 태그를 찾을 수있다!
        // 크롬아! 네이버들어가서 id가 query인 태그를 찾아줄래?
        // WebElement = 웹 페이지 안에 있는 하나의 요소를 자바 객체로
        // 가져와!
        // outerHTML - 시작태그 + 내용 + 끝태그 전부
        WebElement 찾은태그 = driver.findElement(By.id("query"));
        System.out.println(찾은태그.getDomProperty("outerHTML"));

        return "index";
    }

}
/*
 * 
 * // 로그 항상찍기!
 * System.out.println("CrawingController - craw()");
 * 
 * // 1. 웹크롤링을 하기 위한 도구생성
 * // url 가져올 때는 꼭! 본 사이트에 가서 복사해오기!
 * // 크롤러(Crawler): 웹사이트를 돌아다니면서 정보를 수집하는 프로그램
 * // JsoupDocumentReader : 웹 페이지를 (HTML) 읽어서 Spring AI의
 * // Document 형태로 가져온다.
 * 
 * // url -> 웹페이지 HTML -> JsoupDocumentReader
 * // -> HTML태그를 제거! -> 텍스트들만 출력 -> spring ai Document 객체로
 * 
 * // String url =
 * //
 * "https://danawa.com/?srsltid=AfmBOoqytbv4Rg8EgV3KHc954mJGDe484DqLd0zAoom7YLJq3vlmo-UW";
 * String url = "https://www.ediya.com/contents/drink.html";
 * // UrlResource
 * // - url주소를 스프링이 읽은 수 있는 형태로 바꿔준다.
 * 
 * // 외부컴퓨터랑 통신을 할때 URL
 * // - url을 잘못 적어서 인식 못함
 * // - 인코딩이 잘 못되면 인식 못함
 * UrlResource 변경된URL;
 * try {
 * 변경된URL = new UrlResource(url);
 * JsoupDocumentReader 크롤러 = new JsoupDocumentReader(변경된URL);
 * 
 * for (Document 문서 : 크롤러.get()) {
 * System.out.println(문서.getText());
 * }
 * 
 * } catch (MalformedURLException e) {
 * e.printStackTrace();
 * }
 * 
 * // 페이지이동!
 * return "index";
 * 
 * 
 * 
 */