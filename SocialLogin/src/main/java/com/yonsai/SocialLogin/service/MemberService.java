package com.yonsai.SocialLogin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonsai.SocialLogin.entity.Member;
import com.yonsai.SocialLogin.repository.MemberRepository;

@Service
public class MemberService {
    // 로그인 데이터를 저장하는 데이터베이스 직원
    @Autowired
    MemberRepository 디비정보담당자;


    public String 로그인처리해줘(String id, String pw) {
        System.out.println("MemberService - 로그인처리해줘()");

        Member 정보 = 디비정보담당자.findById(id);
        return "";


        
    }
}