package com.yonsai.Day59_20260814.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 웹 요청을 받을 수 있는 파일이다!
// 그냥 자바파일은 웹 요청을 처리할 수없다
// @어노테이션을 붙이고 스프링한테 알려준다.
@Controller
public class HomeController {

    // 메인 페이지보여줘!
    @GetMapping("/index")
    public String index() {
        System.out.println("컨트롤러가 동작했다. index()");

        return "index"; // 파일명만 작성!
    }

    // 아! 웹 요청 받을 수있구나!
    @GetMapping("/admin")
    public String admin() {
        System.out.println("컨트롤러가 동작했다. admin()");
        System.out.println("관리 확인 완료! 관리자페이지 가세요!");

        // admin.html 페이지로 이동해라!
        // return 가고싶은 페이지명
        return "admin";
    }

    // /user 라고 URL 들어오면 실행해라~
    // 파일의 이름 대소문자를 구별한다. 그래서 오타나 대소문자가
    // 섞여서 버그 확률이 높다!
    @GetMapping("/user")
    public String user() {
        System.out.println("컨트롤러가 동작했다. user()");
        return "user/user";
    }

}

/*
 * static 폴더에 넣어놓으면 외부 사람들이 내 중요한
 * 페이지들에 막 접속할 수있다. 그럼 안된다!
 * 
 * 그래서 외부사람이 url로 접근 못하고 컨트롤러한테 확인받고
 * 사이트에 접속 할 수있도록! templates 폴더로 저장한다.
 * 
 * static URL로 접속 가능 (권한 체크 , 로그 확인 X)
 * templates URL로 접속 불가능!
 * 
 * 그러면 중요하지 않은 이미지나 자바스크립트 파일들 static
 * 그외에 처리하는 내용들은 templates관리한다.
 * 
 * 
 * 질문 답
 * 1. vscode , sts , IntelliJ 기본적으로 내부연동 가능하다
 * 2. 회사 자체 템플릿(사내 표준 양식)
 * - 회사 마다 자주 쓰는 라이브러리
 * - 폴더 구조
 * - 공통 설정( 로깅 , 예외처리, 보안 설정)
 * 3. 사내 git 저장소에서 템플릿을 clone 해서 시작한다.
 * 
 * 스프링이 자동으로 프로젝트명을 저장하고 있기 때문에
 * 우리는 return 페이지명만 작성하면 자동으로 경로를 만들어서
 * 템플릿폴더 안에 파일을 찾을 수있도록 해준다.
 * 
 * 자바였으면 직접 경로를 매번! 입력해서 사용해야된다.
 * 
 * 
 * 프리픽스(prefix) - 고정된 주소를 미리 저장
 * 파일명 앞에 나오는 프로젝트명
 * 
 * return "파일명" - 매번 다르게 바뀌는 부분만 입력
 * 
 * 서픽스(suffix) - 고정된 확장자명을 미리 저장한다.
 * 
 * 프리픽스변수 + return된 파일명 + 서픽스 자동으로 붙여서
 * 완성된 전체 경로를 만들어준다.
 */