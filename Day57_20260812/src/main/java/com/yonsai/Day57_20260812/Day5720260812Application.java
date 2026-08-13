package com.yonsai.Day57_20260812;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day5720260812Application {

	public static void main(String[] args) {
		SpringApplication.run(Day5720260812Application.class, args);
	}
}
// 서버 
//  - 컴퓨터 + 그 안에서 24시간 켜져서 요청을 기다리는 프로그램

// 자바랑 브라우저랑(프론트) 데이터를 주고 받을 수 없다!
// 서버(톰캣)라는걸 이용한다.

// 자바는 인터넷과 대화할 능력이 없다!
// 브라우저(Html)랑 자바(java)가 대화를 데이터를 주고 받을려면 공통 언어
// HTTP - 컴퓨터끼리 인터넷에서 대화하는 언어!

// 요청종류   GET 나 데이터 줘!
// 어떤걸 원하는지? /login 로그인 관련 정보줘!
// GET /login HTTP/1.1
// Host: localhost:8000 localhost 이 주소로 요청 보낼께

// 톰캣 -  언어를 알아듣고 통역사

// 실행 순서(파이프라인)
// 1. 브라우저 "네이버메인페이지 보여줘" (HTTP언어로 요청)
// 2. 톰캣 : 이 요청 알아듣고 받음
// 3. 톰캣 : 이 요청, 누가 처리해야하지?
//       요청을 정확히 어떤 코드가 처리해야되는지 판단을 못해
//       우리 프로젝트에는 앞으로 자바 파일이 수백개 생길 수있다.
// 4. 톰캣이 스프링한테 받은 요청을 보낸다. 
// 5. 스프링이 미지 정해둔 정보를 이용해서 자바 코드가 실행될 수있도록
//    한다.



/*

Request (요청) 나 이거해줘! -> Response(응답) 여기 결과!  HTTP는 1회성

다른 데이터가 필요하면 또 요청을 해야된다. 
메인페이지 보여줘 
네이버 웹툰 보여줘(버튼,url)

기안84웹툰 리스트 보여줘!
웹툰
웹툰
웹툰



브라우저                     자바 프로그램
Chrome                       Spring
   │                            │
   │       HTTP 요청             │
   ㅣ─────────────▶│
   │                            │
   │       HTTP 응답             │
   ◀───────────────────────────┤




*/