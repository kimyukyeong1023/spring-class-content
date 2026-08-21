package com.yonsai.Day62_20260820.repository;

import com.yonsai.Day62_20260820.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DB 자동생성 명령문 줘!
// 내가 만든Post 테이블과 관련된 CRUD 명령문줘!

// Repository 이미 다운로드 받아서 설치한 앱!
//             앱 안에 기능들 (조회,이체,출금)
// extends JpaRepository 카카오톡앱 설치하기!
@Repository
public interface PostRepository
    extends JpaRepository<Post, Integer> {
}


// JpaRepository
//  - DB기능이 이미 들어있는 프로그램!
//  = 추가,조회,삭제,수정 미리 다 만들어놨다
//  extends 다운로드해라!