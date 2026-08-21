package com.yonsai.Day60_20260818.dto;

// 게시글 한개를 저장하는 클래스! 
// 데이터 저장
public class Board {
  int no;
  String title;
  String writer;
  String date;
  int readCount;

  // 생성자, getter & setter 등 자동으로 만들어주는 단축키
  // ctrl + .
  public Board(int no, String title, String writer, String date, int readCount) {
    this.no = no;
    this.title = title;
    this.writer = writer;
    this.date = date;
    this.readCount = readCount;
  }

  public int getNo() {
    return no;
  }

  public String getTitle() {
    return title;
  }

  public String getWriter() {
    return writer;
  }

  public String getDate() {
    return date;
  }

  public int getReadCount() {
    return readCount;
  }
  

}