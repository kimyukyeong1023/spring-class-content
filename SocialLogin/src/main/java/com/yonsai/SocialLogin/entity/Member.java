package com.yonsai.SocialLogin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "members")
@Entity // 뭘 저장할래?
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 대표키를 (primary key)
    @Id
    private Long memberId;

    private String id;
    private String pw;

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

    // 다른폴더에서 User데이터를 수정,가져갈수도있다.

}