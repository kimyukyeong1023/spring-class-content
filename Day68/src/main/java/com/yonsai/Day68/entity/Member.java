package com.yonsai.Day68.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;
    private String password;

    //간편로그인
    private String provider; //어디서 로그인했는지?
    private String providerId;  //제공사에서 발급한 그 사람의 고유한 id저장

    //아주 적은 확률로 구글의 아이디와 카카오의 아이디가 같을 경우 있을 수도 있기 때문에
    //내 서버에 저장되는 id값이 중복되는 현상이 있을수 있기 때문에,
    //String username = "kakao_12345678"라는 식으로 회사명 써서 저장하는 방법도 있음.


    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}