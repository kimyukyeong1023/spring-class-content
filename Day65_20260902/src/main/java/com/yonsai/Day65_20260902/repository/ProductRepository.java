package com.yonsai.Day65_20260902.repository;

import com.yonsai.Day65_20260902.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 이걸 왜 만들지?

public interface ProductRepository extends JpaRepository<Product, Long> {
  // 자동으로 추가 save(),findAll(),delete()

  // 원하는 컬럼을 조회하고싶으면 직접 만들어!
  // 규칙!
  // findBy컬럼명

  // 1. 카테고리로 검색
  List<Product> findByCategory(String category);

  // 2.가격 검색
  List<Product> findByPrice(int price);

  // 3. 상품명 검색
  List<Product> findByProductName(String productName);
}



/*
레이어드 아키텍처 
 - 벡앤드 개발에서 널리 사용되는 표준 패턴 

사용자 요청을 보냄
     ↓
컨트롤러 요청 받음
     ↓ (서비스한테 부탁!)
서비스가 처리
     ↓
레포지토리가 DB에서 데이터를 꺼내옴
     ↓ (Entity 구조대로)
Entity 모양의 데이터가 나옴

Entity  = DB에서 데이터를 꺼내오는 그 순간! 엔티티가 만들어진다.
DTO = 클래스 파일들끼리 여러데이터를 한번에 묶어서 보내려고! 
*/