package com.yonsai.aws.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.yonsai.aws.dto.Item;

import jakarta.annotation.PostConstruct;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import tools.jackson.databind.JsonNode;
import tools.jackson.dataformat.xml.XmlMapper;

// 채팅이 오면 먼저 문서들을 임베딩
// 백터DB에 저장한다. 

// 서버가 켜지는 순간 벡터DB생성하고 데이터 채워야된다.
// XML도 공공데이터 포털에서 데이터를 가져와야된다.

// @PostConstruct 
//  - 스프링이 객체들을 생성하고 나서 자동으로 함수를 
//    호출해서 실행하는 자동화도구중에 하나다!
//  - 서버가 켜지면 딱! 한번만 실행! 매 요청마다 매번 XML을 
//    불러올 필요도 없다!
//  - 자바파일 안에서 딱! 한번만 설정

@Service
public class RagService {

    // 임베딩 도구 가져오기!
    @Autowired
    private EmbeddingModel embeddingModel;

    // yaml 파일에 있는 API-KEY 자바 변수에 넣어야된다.
    @Value("${public-data.service-key}")
    private String serviceKey;

    private VectorStore vectorStore;

    @PostConstruct
    void init() {
        System.out.println("서버가 시작! 대출 상품 데이터 준비시작!");
        xmlParser();
    }

    // 공공데이터로 가져온 데이터들이 값이 없어서 null을 반환
    // null이 오면 빈 문자로 변환하는 함수!
    String safeText(JsonNode item, String field) {
        JsonNode 임시 = item.get(field);

        if (임시 == null) {
            return "";
        }
        return 임시.asString();
    }

    // XML이용한 데이터 가져오기
    void xmlParser() {
        System.out.println("XmlController - loan()");

        // 1. 도구들 가져오기!
        RestClient Http통신도구 = RestClient.create();
        XmlMapper xml을읽는도구 = XmlMapper.builder().build();

        // 2. 먼저 데이터 가져오기 (통신)
        String url = "http://apis.data.go.kr/1160100/service/GetSmallLoanFinanceInstituteInfoService/getOrdinaryFinanceInfo?"
                + "serviceKey=" + serviceKey
                + "&pageNo=1"
                + "&numOfRows=100";
        System.out.println(url);

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

        List<Item> loanList = new ArrayList<>();

        for (var item : itemsList) {
            Item loan = new Item(
                    safeText(item, "finPrdNm"), // 청년 대출
                    safeText(item, "lnLmt"), // 없으면 ""
                    safeText(item, "irtCtg"), // 없으면 ""
                    safeText(item, "irt"), // 3.5%
                    safeText(item, "rdptmthd"), // 없으면 ""
                    safeText(item, "trgt")); // 없으면 ""
            loanList.add(loan);

        }
        System.out.println("파싱한 개수: " + loanList.size());
        // 벡터DB부르기!
        changeVectorDb(loanList);
    }

    // 벡터DB에 저장
    void changeVectorDb(List<Item> loanList) {
        // spring ai 가 읽을 수있게 Document로 변경해야된다.
        List<Document> docs = new ArrayList<>();

        // 벡터DB에 설정(임베딩세팅)해서 객체 생성하기
        vectorStore = SimpleVectorStore.builder(embeddingModel)
                .build();

        // 대출 상품 하나당 문서 하나가 생긴다!
        // 대출 상품을 하나씩 꺼내기!
        for (Item 상품한개 : loanList) {
            // Ai가 검색할 상품 설명 만들기
            String 내용 = "상품명:" + 상품한개.getFinPrdNm()
                    + "\n대출한도: " + 상품한개.getLnLmt()
                    + "\n금리: " + 상품한개.getIrt()
                    + "\n금리구분: " + 상품한개.getIrtCtg()
                    + "\n상환방법: " + 상품한개.getRdptmthd()
                    + "\n지원대상: " + 상품한개.getTrgt();

            // 부가정보 없으면 내용만 담기!
            docs.add(new Document(내용));
        }
        // 백터DB에 저장하기
        vectorStore.add(docs);
    }

    // 유사도 검색하기
    // AI에게 질문 보내기!
    public String search(String 질문) {
        // 1. 벡터 db 검색
        List<Document> 찾은문서들 = vectorStore.similaritySearch(질문);

        // 2. 찾은 문서의 개수 확인
        System.out.println("찾은 문서 개수: " + 찾은문서들.size());

        // 찾은 내용들을 문자로 변경하기!
        String context = 찾은문서들.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        // 3. 컨트롤러도 문서 보내기
        return context;
    }
}
