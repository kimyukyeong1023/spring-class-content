package com.yousai.pgvector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yousai.pgvector.service.HotelService;
import com.yousai.pgvector.test.HotelDummyService;

@Controller
public class HotelController {

  @Autowired
  private HotelDummyService 테스트;

  @Autowired
  private HotelService service;

  @GetMapping("/hotel")
  public String hotelSearch(@RequestParam("desc") String desc) {
    System.out.println("HotelController - hotelSearch() ");
    System.out.println("검색어: " + desc);

    // 서비스한테 일시키기!
    service.hotelSearch(desc);

    return "index";
  }

  @GetMapping("/data")
  public String data() {

    테스트.insertDummyData();
    return "index";
  }

}
