package com.yonsai.Day63_20260821.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonsai.Day63_20260821.entity.Attendance;
import com.yonsai.Day63_20260821.repository.AttendanceRepository;

// 관리자 페이지에서 실질적으로 일을 처리하는 파일(클래스)!
// 출결조회/통계/수정을 보여주려고 만든다. 
@Service
public class AdminService {

  // 출결테이블 관리하는 담당자 대려와!
  @Autowired
  AttendanceRepository 출결담당자;

  public List<Attendance> 학생조회전부() {
    System.out.println("AdminService - 학생조회전부()");

    // 출결담당자야 전부찾아와!
    // 여러명을 보내야된다.
    List<Attendance> list = 출결담당자.findAll();

    // 서비스가 컨트롤러한테 보낸다.
    return list;
  }

}