package com.yonsai.Day59_20260814.controller;

import java.util.ArrayList;

import com.yonsai.Day59_20260814.dto.Person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 웹 요청을 처리하는 클래스야!
// DispatcherServlet - 관제탑! (공항)
//  이런 요청들어오면 어떤 컨트롤러가 실행해야되! 

@Controller
public class MemberController {

    @GetMapping("/test1")
    public String test1(Model 상자) {

        // 1.값
        String name = "에리나";

        // 2.HTML로 데이터 보내기
        상자.addAttribute("username", name);

        // 3.타임리프테스트폴더 안에 test1.html 페이지 이동
        return "thymeleafTest/test1";
    }

    @GetMapping("/")
    public String test2(Model 상자) {

        // 1. 값
        String name = "그레이스";
        int age = 20;
        double wei = 40.25;

        // 2. HTML파일로 보내기
        상자.addAttribute("name", name);
        상자.addAttribute("age", age);
        상자.addAttribute("wei", wei);

        return "thymeleafTest/test2";
    }

    @GetMapping("/array")
    public String testArray(Model 상자) {

        // 1. 값
        int[] arr1 = { 1, 2, 3, 4, 5 };
        double[] arr2 = { 1.1, 2.2, 3.3 };
        String[] arr3 = { "김동현", "김나나", "김석동" };

        // 2. HTML 보내기
        상자.addAttribute("arr1", arr1);
        상자.addAttribute("arr2", arr2);
        상자.addAttribute("arr3", arr3);

        ArrayList<String> urls = new ArrayList<>();

        urls.add("https://www.naver.com");
        urls.add("https://www.google.com");
        urls.add("https://www.daum.com");

        // url 경로를 모아서 메뉴탭을 만든다.

        // HTML로 url들 보내기
        상자.addAttribute("urls", urls);
        // 무료 이미지 사이트의 물건 이미지 3개
        ArrayList<String> images = new ArrayList<>();

        images.add("https://commons.wikimedia.org/wiki/Special:Redirect/file/Coffee_in_a_mug.jpg?width=600");
        images.add("https://commons.wikimedia.org/wiki/Special:Redirect/file/Wristwatch.jpg?width=600");
        images.add("https://commons.wikimedia.org/wiki/Special:Redirect/file/Se_bluetooth_headset.jpg?width=600");

        // HTML로 이미지 경로들 보내기
        상자.addAttribute("images", images);

        return "thymeleafTest/array";
    }
    // 자바에서 가져오면 상황마다 다르게 만들수있다.
    // 이미지들이 크기가 너무 크기 때문에 실제 Mysql에서 저장이
    // 힘들다. 클라우드 드라이브, AWS S3 이용해서 데이터를
    // 저장하고 url들을 mysql에 저장해서 자바가 꺼내오는 방식!

  @GetMapping("/object")
  public String testObject(Model 상자) {

    Person p1 = new Person("김민수", 20, "ENFP");
    Person p2 = new Person("이서연", 25, "ISFJ");
    Person p3 = new Person("박준호", 30, "INTP");

    상자.addAttribute("p1", p1);

    ArrayList<Person> personList = new ArrayList<>();
    personList.add(p1);
    personList.add(p2);
    personList.add(p3);

    상자.addAttribute("personList", personList);

    return "thymeleafTest/object";
  }

}
/*
 * html 파일은 두가지 종류로 나뉜다.
 * 고정된 페이지 (정적 파일)
 * - 회사소개 페이지 (누가 보든 언제 보든 내용이 항상 똑같다)
 * - 자기소개/포트폴리오 페이지 (본인이 직접 수정하기 전까지는 항상 동일)
 * - 이용약관,개인정보처리방침( 법적 문서라 자주 안 바뀌고 모두한테 동일)
 * - 로고,아이콘,css파일 ,폰트파일 (디자인 요소는 항상 고정)
 * 
 * 매번 바뀌는 페이지(동작 파일)
 * - 네이버 실시간 검색 결과 - 검색어마다 결과가 다름
 * - 쿠팡 "내 주문 내역" - 사용자마다 완전 다른 데이터
 * - 은행 "잔액 조회" - 조회할 때마다 실시간으로 달라짐
 * - 인스타그램 피드 - 새로고침할 때마다 새 게시물이 추가될 수있음
 * - 유튜브 동영상 - 새로고침을 하거나 앱을 다시 켤때마다 새로운 영상!
 * - 로그인 후 - "이서희님 환영합니다."
 * 
 * 매번 바뀌는 페이지를 만들기 위해서 새로운 라이브러리를 추가했다.
 * thymeleaf (타임리프)
 * - 자바의 변수값을 HTML태그 안에 넣어라!
 * - 리액트에서 {변수명} 들어간것처럼(바인딩)
 * 
 * 라이브러리 추가(agents.md) 파일에 포함!
 * 
 * 
 * 
 * package com.yonsai.Day59_20260814.controller;
 * 
 * import org.springframework.stereotype.Controller;
 * import org.springframework.ui.Model;
 * import org.springframework.web.bind.annotation.GetMapping;
 * import org.springframework.web.bind.annotation.RequestParam;
 * 
 * // 웹 요청을 처리하는 클래스야!
 * // DispatcherServlet - 관제탑! (공항)
 * // 이런 요청들어오면 어떤 컨트롤러가 실행해야되!
 * 
 * @Controller
 * public class MemberController {
 * 
 * @GetMapping("/")
 * public String test1(Model 상자) {
 * 
 * // 동적페이지 필요한거 두개
 * // 1. 데이터(값)
 * String name = "에리나";
 * 
 * // 2. 컨트롤러 -> HTML파일에게 데이터를 전달할 때 사용하는
 * // 타입 Model
 * // Model - spring이 자동으로 만들어서 넣어주는 데이터 전달 상자!
 * 상자.addAttribute("username", name);
 * 
 * // 타임리프테스트폴더 안에 test1.html 페이지 이동
 * return "thymeleafTest/test1";
 * }
 * 
 * }
 */
