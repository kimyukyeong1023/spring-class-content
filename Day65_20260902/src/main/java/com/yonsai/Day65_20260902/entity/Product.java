package com.yonsai.Day65_20260902.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// 쇼핑몰에 상품검색,가격,카테고리 검색 기능구현!
// 저장할 데이터들이 필요하다! 
// 자바는 데이터베이스 조작 못한다.
// 도구를 빌려와요! JPA
// 나 쓸려면 규칙있다! 
// 테이블 구조줘야되! Entity

// 기본적으로 제공하는 id변수에 대한 기본 함수만 제공!
// 카테고리로 검색, 가격이 얼마 이상인 상품 이런 내용들은 
// 함수가 안 만들어졌다
// 그래서 내가 직접만든다. 

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int price;
  private String productName;
  private String category;

  public Product() {
  }

  public Product(int price, String productName, String category) {
    this.price = price;
    this.productName = productName;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}