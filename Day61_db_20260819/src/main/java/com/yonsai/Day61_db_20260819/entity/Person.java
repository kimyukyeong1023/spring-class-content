package com.yonsai.Day61_db_20260819.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// JPA
//  - sql 코드 자동으로 작성해줌
//  - 테이블도 자동으로 생성해줌
//  데이터베이스에 관한 모든 내용은 자동화해줄래?!

// 내가 클래스로 틀 만들어놓을꼐 그대로 테이블 생성도 해줘!
//  테이블 생성기 대표키! 설정해달라 primary key 
@Entity
public class Person {

  // 대표키(PK)
  @Id
  private int id;

  private String name;
  private int age;
}