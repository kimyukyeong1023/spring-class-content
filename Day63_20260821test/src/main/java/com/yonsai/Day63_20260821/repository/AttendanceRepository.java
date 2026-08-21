package com.yonsai.Day63_20260821.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yonsai.Day63_20260821.entity.Attendance;

// Attendance출결 관리 직원! 메뉴얼교육(JPA)
// 에러발생하는 이유? 
//  - 어떤 Entity를 관리하는지 몰라서!
@Repository
public interface AttendanceRepository
    extends JpaRepository<Attendance, Integer> {

}