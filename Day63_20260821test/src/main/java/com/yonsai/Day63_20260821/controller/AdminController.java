package com.yonsai.Day63_20260821.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonsai.Day63_20260821.dto.AttendanceResponseDTO;
import com.yonsai.Day63_20260821.service.AdminService;


@Controller
public class AdminController {
    @Autowired
    AdminService 출결관리자;

    @GetMapping("/admin")
    public String admin(Model 상자) {
        ArrayList<AttendanceResponseDTO> responceDto=출결관리자.학생조회전부();
        상자.addAttribute("list", responceDto);

        return "admin";
    }
    
}