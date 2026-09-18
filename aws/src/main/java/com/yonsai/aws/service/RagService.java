package com.yonsai.aws.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.yonsai.aws.dto.Item;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import tools.jackson.dataformat.xml.XmlMapper;

// 채팅이 오면 먼저 문서들을 임베딩
// 백터DB에 저장한다. 
// 

@Service
public class RagService {

    // 임베딩 도구 가져오기!
    @Autowired
    private EmbeddingModel embeddingModel;

    // yaml 파일에 있는 API-KEY 자바 변수에 넣어야된다.
    @Value("${public-data.service-key}")
    private String serviceKey;

    private VectorStore vectorStore;

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
                    item.get("finPrdNm").asString(),
                    item.get("lnLmt").asString(),
                    item.get("irtCtg").asString(),
                    item.get("irt").asString(),
                    item.get("rdptmthd").asString(),
                    item.get("trgt").asString());
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

        String context = 찾은문서들.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));
        return context;
    }
}