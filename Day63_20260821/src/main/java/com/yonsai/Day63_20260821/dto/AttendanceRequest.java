package com.yonsai.Day63_20260821.dto;

import lombok.Getter;
import lombok.Setter;

//학생 이름을 입력하면 서버로 보내기(주문서 양식!)
// HTML에서 넘어오는 데이터를 저장하는 타입!
// 나머지는 시간,출석상태 판정 (Service)
// HTML,자바,Mysql 변수명을 동일하게 가져가는게 좋다!(자동화)
@Getter
@Setter
public class AttendanceRequest {

  private String studentName;
}