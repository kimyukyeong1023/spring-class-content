package com.yonsai.Day63_20260821.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonsai.Day63_20260821.dto.AttendanceResponseDTO;
import com.yonsai.Day63_20260821.entity.Attendance;
import com.yonsai.Day63_20260821.entity.Student;
import com.yonsai.Day63_20260821.repository.AttendanceRepository;

// 핵심로직 - 지각 판정 또는 출석했다.(주방장)
@Service
public class AttendanceService {

  // 출결테이블 관리하는 담당자 대려와!
  @Autowired
  AttendanceRepository 출결담당자;

  public AttendanceResponseDTO 출결등록(String studentName) {
    System.out.println("AttendanceService - 출결등록()");

    // 디비에 저장되기 전에 챙겨 될 데이터들!
    // 현재 시간, 그 시간에 따라서 출석,결석,지각,조퇴
    LocalDateTime 현재시간 = LocalDateTime.now();

    // 지각 판정 기준 9시
    // 기준 시간을 비교하고 싶을 때는 LocalTime 시간만 저장
    LocalTime 지각기준시간 = LocalTime.of(9, 0);

    // isAfter() ~보다 이후인가? ~보다 뒤인가?
    // 결과 true 맞아!늦었어! false 틀려! 안 늦었어!
    String 상태 = 현재시간 // 날짜,년도,시간,분,초 다 가져옴
        .toLocalTime() // 거기서 시간만 꺼내!
        .isAfter(지각기준시간) ? "지각" : "출석";

    // 현재시간과 상태와 이름을 한꺼번에 묶어서 디비로 보내기
    Attendance 학생출결정보 = new Attendance();
    Student 학생정보= new Student();
    학생정보.setStudent_name(studentName);
    학생출결정보.setStudent(학생정보);
    학생출결정보.setStatus(상태);
    학생출결정보.setCheckTime(현재시간);

    // Attendance 담당자한테 추가해라!
    출결담당자.save(학생출결정보);

    AttendanceResponseDTO 출결응답= new AttendanceResponseDTO();
    출결응답.setStudentName(studentName);
    출결응답.setCheckTime(현재시간);
    출결응답.setStatus(상태);

    return 출결응답;
  }
}