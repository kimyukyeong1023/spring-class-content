package com.yousai.pgvector.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long hotelId;
  private String name;
  private String description;
  private String location;
  private Integer price;

  public Hotel() {
  }

  // 매개변수를 받는 생성자를 만든다.

  public Long getHotelId() {
    return hotelId;
  }

  public Hotel(String name, String description, String location, Integer price) {
    this.name = name;
    this.description = description;
    this.location = location;
    this.price = price;
  }

  public void setHotelId(Long hotelId) {
    this.hotelId = hotelId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }
}
