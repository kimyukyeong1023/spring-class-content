package com.yonsai.Day63_20260821.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonsai.Day63_20260821.dto.AttendanceResponseDTO;
import com.yonsai.Day63_20260821.entity.Attendance;
import com.yonsai.Day63_20260821.entity.Student;
import com.yonsai.Day63_20260821.repository.AttendanceRepository;

// 관리자 페이지에서 실질적으로 일을 처리하는 파일(클래스)!
// 출결조회/통계/수정을 보여주려고 만든다. 

@Service
public class AdminService {
    @Autowired
    AttendanceRepository 출결담당자;

    public ArrayList<AttendanceResponseDTO> 학생조회전부() {
        System.out.println("AdminService-");
        ArrayList<AttendanceResponseDTO> AttendList = new ArrayList<>();

        for (Attendance 출결정보 : 출결담당자.findAll()) {
            AttendanceResponseDTO ResponseDTO = new AttendanceResponseDTO();
            Student 학생정보= new Student();
            ResponseDTO.setStudentName(학생정보.getStudent_name());
            ResponseDTO.setCheckTime(출결정보.getCheckTime());
            ResponseDTO.setStatus(출결정보.getStatus());
            
            AttendList.add(ResponseDTO);
        }

        return AttendList;

    }

}