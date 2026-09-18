package com.yousai.pgvector.entity;

import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stu_id")
  private Long stuId;
  private String name;
  private Integer age;
  private String address; 

  public String getAddress() {
    return address;
  }

  public Student() {
  }

  public Student(String name, Integer age, String address) {
    this.name = name;
    this.age = age;
    this.address = address;
  }

  public Long getStuId() {
    return stuId;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  public void setStuId(Long stuId) {
    this.stuId = stuId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
// 임베딩 숫자 저장
// 1) 아래처럼 옵션을 설정하고
// 2) 백터를 활성화! on 한번은 스위치를 켜듯 활성하는 명령을 디비버 프로그램에서 해야된다.
// @JdbcTypeCode(SqlTypes.VECTOR) // postgreSql한테 백터타입으로 저장해줘
// @Array(length = 1536) // 질문의 개수
// private float[] embedding;