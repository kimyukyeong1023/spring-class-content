package com.yonsai.Day62_20260820.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

//게시글 한 건을 저장하는 자바 파일
// 제목, 내용, 조회수, 작성한 날짜
// 엔티티(entity)
//  테이블 자동 생성하는 폴더!
//  자바랑 mysql 연결해서 테이블 생성하는 폴더

// 테이터베이스에 저장할 때 테이블 이름 내가 직접 작성

// 테이블이름설정
@Table(name = "posts")

// 테이블 생성
@Entity
@Getter
public class Post {

  // 숫자 증가 하는 auto_increment 설정
  // strategy 방식
  // GenerationType 생성방식
  // IDENTITY mysql auto_increment 쓸꺼야!
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private int id;
  private String title;
  private String content;
  private String create_at;

  // 빈 생성자를 이용해서 비어진 변수 하나만 만들어달라!
  // 그러면 JPA에 mysql을 조회해서 원하는 값들만 저장할 수있게
  // 해준다.

  // JPA는 값을 조회할때 기본생성자만 사용한다(빈 변수 )
  public Post() {
  }

  // title,content,create_at 3개의 정보만 생성해서
  // 보내기
  public Post(String title, String content, String create_at) {
    this.title = title;
    this.content = content;
    this.create_at = create_at;
  }


}

// DB - 실제 데이터가 저장되는 공간
// Repository - DB 작업을 요청하는 곳!
// JpaRepository - 저장/조회/수정/삭제 기능이 이미 만들어져있는 도구!
// @Autowired - 만들어진 Repository객체를 가져와서 실행!