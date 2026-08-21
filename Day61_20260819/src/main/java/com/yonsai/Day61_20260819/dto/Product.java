package com.yonsai.Day61_20260819.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// outline
//  - 현재 파일 안에 클래스,메서드,변수 구조를 한눈에 보여주는 창

@AllArgsConstructor // 전부 받는 생성 (변수개수 모두!)
@NoArgsConstructor // 기본 생성자(변수 아무것도 안받음)
@Getter
@Setter
public class Product {
  // 외부에서 접근 못한다. 그러기때문에 getter,setter
  private int id;
  private String name;
  private String category;
  private int price;
  private int stock;
  private String imageUrl;

}