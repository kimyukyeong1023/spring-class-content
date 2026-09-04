package com.yonsai.SocialLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonsai.SocialLogin.entity.Member;
import java.util.List;


// 너는 멤버들을 관리하는 데이터베이스 직원!
// 이 직원도 메뉴얼 습득! (JPA)
public interface MemberRepository
    extends JpaRepository<Member, Long> {
  // 자동으로 MemberRepository 중괄호 안에
  // save(),findAll(),delete() 기능이 자동으로 만들어진다.
  Member findById(String id);

  Member findByPw(String pw);


}