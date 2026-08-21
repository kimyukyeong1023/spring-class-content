package com.yonsai.Day62_20260820.entity;

import jakarta.persistence.*;

@Entity
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int postId; // 어느 게시글에 눌렀는지?
  private String userId; // 누가 눌렀는지?
}