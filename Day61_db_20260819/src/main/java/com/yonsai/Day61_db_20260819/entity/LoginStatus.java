package com.yonsai.Day61_db_20260819.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// 로그인 기록을 저장 

@Entity
public class LoginStatus {

  @Id
  private int id;

  private boolean isLogin;
  private String user_id;
  private LocalDateTime create_at;
}