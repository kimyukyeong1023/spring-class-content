package com.yonsai.Day60_20260818.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.yonsai.Day60_20260818.dto.Board;

// 게시글 보여줘! 웹 요청이 들어오면 실제 처리하는 클래스!
// 실제 일을 하는 클래스!
@Service
public class BoardService {

    // 메인페이지가 열릴때 게시글을 컨트롤러한테 보내야되야된다.
    // 번호,제목, 작성자,작성일, 조회수
    // 문제가 return 하나만 보낸다.
    // return index,return main,return "로그인성공"
    // 그래서 게시글 한개 정보를 묶어서 보낸다. new Board(번호,제목..)

    // 데이터를 저장도 하는 서비스 클래스!(오늘만)
    ArrayList<Board> list = new ArrayList<>();

    // BoardService객체를 생성하면 생성자에서 자동으로
    // 데이터를 저장한다.
    public BoardService() {
        list.add(new Board(1, "게시판 첫 글입니다", "관리자", "2026-08-08", 5));
        list.add(new Board(2, "안녕하세요 처음 인사드립니다", "정하은", "2026-08-09", 18));
        list.add(new Board(3, "스택오버플로우 활용법", "최유진", "2026-08-09", 29));
        list.add(new Board(4, "오늘부터 부트캠프 시작합니다!", "박지훈", "2026-08-10", 12));
        list.add(new Board(5, "코딩테스트 사이트 추천", "김민준", "2026-08-10", 67));
        list.add(new Board(6, "개발자 취업 준비 꿀팁 공유", "이서희", "2026-08-11", 120));
        list.add(new Board(7, "이력서 첨삭 부탁드려요", "윤도현", "2026-08-11", 38));
        list.add(new Board(8, "JPA vs MyBatis 뭐가 더 나을까요", "강태양", "2026-08-12", 91));
        list.add(new Board(9, "오늘 수업 내용 정리 공유합니다", "정하은", "2026-08-12", 73));
        list.add(new Board(10, "REST API 설계 어떻게 하시나요", "최유진", "2026-08-13", 45));
        list.add(new Board(11, "프로젝트 팀원 구합니다 (3명)", "박지훈", "2026-08-13", 102));
        list.add(new Board(12, "Git 브랜치 전략 추천해주세요", "김민준", "2026-08-14", 27));
        list.add(new Board(13, "알고리즘 스터디 모집합니다", "윤도현", "2026-08-14", 61));
        list.add(new Board(14, "MySQL 설치하다가 에러났어요", "이서희", "2026-08-15", 34));
        list.add(new Board(15, "오늘 점심 뭐 드셨나요", "강태양", "2026-08-15", 9));
        list.add(new Board(16, "컨트롤러랑 서비스 분리 이유 궁금해요", "정하은", "2026-08-16", 56));
        list.add(new Board(17, "타임리프 th:each 질문있습니다", "최유진", "2026-08-16", 23));
        list.add(new Board(18, "자바 컬렉션 정리 (List, Set, Map)", "박지훈", "2026-08-17", 88));
        list.add(new Board(19, "오늘 날씨 진짜 덥네요", "김민준", "2026-08-17", 15));
        list.add(new Board(20, "스프링 부트 처음 시작하는 분들께", "이서희", "2026-08-18", 42));

    }

    public ArrayList<Board> selectAll() {

        return list;
    }

    public ArrayList<Board> findAll(String type, String keyword) {
        // 1. 로그
        System.out.println("BoardService - findAll()");

        // 2. 결과 담을 변수 생성
        ArrayList<Board> result = new ArrayList<>();

        // 3 . 반복해서 찾아라!
        for (Board b : list) {
            // 작성자 이름에 검색어가 포함되어있으면 결과에 담기!
            // 문자 포함되어있니?
            // 4. 찾은 값을 따로 저장
            String searchType = "";

            switch (type) {
                case "writer":
                    searchType = b.getWriter();
                    break;

                case "title":
                    searchType = b.getTitle();
                    break;

            }

            if (searchType.contains(keyword)) {
                result.add(b);
            }
        }
        // 5. 컨트롤러에게 주기!
        return result;
    }

}