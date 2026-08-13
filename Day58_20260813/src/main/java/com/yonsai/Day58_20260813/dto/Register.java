package com.yonsai.Day58_20260813.dto;

public class Register {
  private String id;
  private String pw;
  private String phone;
  private String address;
  private String email;
  private String gender;

  public Register() {
  }

  public Register(String id, String pw, String phone, String address, String email, String gender) {
    this.id = id;
    this.pw = pw;
    this.phone = phone;
    this.address = address;
    this.email = email;
    this.gender = gender;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPw() {
    return pw;
  }

  public void setPw(String pw) {
    this.pw = pw;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "Register [id=" + id + ", pw=" + pw + ", phone=" + phone + ", address=" + address + ", email=" + email
        + ", gender=" + gender + "]";
  }
}