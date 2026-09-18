package com.yonsai.Day68.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonsai.Day68.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
