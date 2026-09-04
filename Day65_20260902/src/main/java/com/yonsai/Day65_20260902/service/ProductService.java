// package com.yonsai.Day65_20260902.service;

// import com.yonsai.Day65_20260902.entity.Product;
// import com.yonsai.Day65_20260902.repository.ProductRepository;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class ProductService {

//   @Autowired
//   ProductRepository 디비;

//   void 상품검색(String productName){

//       // 디비에서 Entity이용해서 한번 가져왔으면 
//       // 자바코드로 어떻게 저장할꺼냐 
//       List<Product> 검색결과들  = 디비.findByProductName(productName);
//   }


  
//   // 서비스는 뭐지? 
//   //  DB - 데이터베이스로 가기 위한 준비하는 곳!
//   //     디비에서 원하는 클래스파일들!
//   // AI - AI로 가기 위한 준비하는 곳 
//   //     AI가 필요한 클래스파일들!
//   // 다른 컴퓨터로 이동하기 위한 준비하는 곳!
//   //     다른컴퓨터랑 통신하기 위한 클래스파일들!
//   // 서비스에 필요한 도구들을 추가

// }