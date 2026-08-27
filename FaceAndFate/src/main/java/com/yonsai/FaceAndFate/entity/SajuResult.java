package com.yonsai.FaceAndFate.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// 사주/관상 결과를 저장하는 타입
// 생년월일도 저장하고 결과도 저장하고 언제 봤는지?
// entity 폴더는 저장해야되는 데이터들이 모여있다.
// 각각 변수로 저장을 하면 여러 사람들이 중복되는
// 변수들이 생길 수도 있고 변수의 수가 기하급수적으로
// 생길 수있다. 그래서 한명의 사주 정보다!
// 클래스 묶어서 하나의 타입을 만든다.


@Entity   // SqjuResult 자바클래스 파일을 DB테이블로 쓸거야!
public class SajuResult {

  // 대표키 primary key 
  //  @GeneratedValue  번호를 자동으로 저장해라!
  //  GenerationType.IDENTITY  mysql auto_increment 설정추가!
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate birthDate;

  @Column(length = 500)   // varchar(500)
  private String aiResult;

  private LocalDateTime createAt = LocalDateTime.now();

    public SajuResult(){
  
  }

  public SajuResult(LocalDate birthDate, String aiResult){
    this.birthDate=birthDate;
    this.aiResult=aiResult;

    
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getAiResult() {
    return aiResult;
  }

  public void setAiResult(String aiResult) {
    this.aiResult = aiResult;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }
}