package com.yonsai.Day63_20260821;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day6320260821Application {

  public static void main(String[] args) {
    SpringApplication.run(Day6320260821Application.class, args);
  }

}

/*
 * 기획
 * MVP 개발 플로우
 * - 최소한의 기능으로 실제 사용할 수있는 제품!
 * - 출석을 기록하고 조회할 수있게 만들자!
 * 
 * 1. "무엇을 저장할지" DB구조 - entity
 * 누가(학생 이름? 학번? 아이디?)
 * 언제(출석한 날짜/시간)
 * 상태(출석/지각/결석/조퇴)
 * 순서대로 저장 (자동으로)
 * 
 * -2) 변수명 작성
 * studentName
 * checkTime
 * status
 * id
 * 디비 이름? AttendanceSystem
 * 테이블이름? - Attendance
 * 
 * -3) 타입 작성
 * String,LocalDateTime,String, int
 * 
 * 
 * 
 */

/*
 * 아이디어
 * ↓
 * 요구사항 정리(핵심 기능)
 * [학생]
 * - 학생 등록
 * - 학생 목록 조회
 * 
 * URL 주소 정하기
 * 프론트에서 서버로 요청해야하는 주소정하기 
 * [출결]
 * - 출석체크
 * - 출결조회
 * - 출결수정
 * ↓
 * DB설계 (Entity) -> 자동으로 번역해서 실행(JPA)
 * ↓
 * Bankend 개발
 * Springboot로 역할을 나눈다.
 * controller , service, repository , mysql
 * 
 * API개발
 * 출석등록 POST /Attendanc
 * 출결조회 GET /AttendancAll
 * 학생조회 GET /students
 * 
 * ↓
 * Frontend 개발
 *  From 태그로 name으로 데이터보내기!
 * ↓
 * 연결 및 테스트
 * ↓
 * MVP 완성
 * ↓
 * 추가 기능 개발
 * 
 */