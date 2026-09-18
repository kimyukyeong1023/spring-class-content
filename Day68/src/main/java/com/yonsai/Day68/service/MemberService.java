package com.yonsai.Day68.service;

import com.yonsai.Day68.entity.Member;
import com.yonsai.Day68.repository.MemberRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 암호화하는 객체 가져오기(스프링 컨테이너에서 주입받기)
    @Autowired
    private PasswordEncoder encoder;

    // 저장
    public void memberSave(String pw) {
        System.out.println("MemberService - memberSeve()");
        // 1. 일단 암호화!
        String 암호화된비밀번호 = encoder.encode(pw);

        // 2. entity 집어넣기
        Member 유저 = new Member();
        유저.setMemberId("qwer");
        유저.setPassword(암호화된비밀번호);

        // 3. 디비로 보내기!
        memberRepository.save(유저);
    }

    public void login(String pw) {
        System.out.println("MemberService - login()");
        // 1. 아이디로 조회 (회원 찾기)
        Member 유저 = memberRepository.findById(1L).get();

        // 2. 입력한 비밀번호 vs DB에 저장되어있는 암호화 비밀번호 비교!
        // matches(사용자가입력한비밀번호,DB에 저장된 암호화된 비밀번호)
        // boolean 타입으로 결과를 알려준다
        boolean 결과 = encoder.matches(pw, 유저.getPassword());
        System.out.println(결과);
    }

}