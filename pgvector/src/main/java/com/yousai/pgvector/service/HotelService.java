package com.yousai.pgvector.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yousai.pgvector.entity.Hotel;
import com.yousai.pgvector.repository.HotelRepository;

@Service
public class HotelService {

  @Autowired
  private VectorStore 벡터저장소;

  @Autowired
  private HotelRepository 호텔디비;

  @Autowired
  private EmbeddingModel 숫자배열변경;

  public void hotelSearch(String desc) {
    System.out.println("HotelService - hotelSearch() ");

    // 1. 요청객체 만들기
    SearchRequest 요청객체 = SearchRequest.builder()
        .query(desc)
        .topK(3) // 유사도 검색을 하면 상위 몇개 가져올지
        .build();
    // 2. 보내기
    List<Document> 결과 = 벡터저장소.similaritySearch(요청객체);

    System.out.println(결과);
  }

  // 가짜 데이터를 추가하는 함수!
  public void add(String name, String description, String location, Integer price) {
 
    
    // 1) hotel테이블에 저장
    // hotelId발급
    Hotel hotel = 호텔디비.save(new Hotel(name, description, location, price));

    // 2) vector_store테이블에 저장
    // 임베딩 대상 -> 내용(description)
    // 나머지 부가 정보 -> hotelId를 이용해서 실질적인 호텔의 정보를
    // 가져올려고!
    Document document = new Document(
        description, // ★★★★★ 이 텍스트가 임베딩됨 ★★★★★
        Map.of("hotelId", hotel.getHotelId()));
    벡터저장소.add(List.of(document));
  }
}
