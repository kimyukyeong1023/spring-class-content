package com.yonsai.first.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
//자바는 웹통신을 못한다.
//서버는 유저가 데이터를 요청하면 데이터를 보내주는 프로그램일뿐!
//서버야 이거해줘!

//자바랑 브라우저랑 데이터를 주고 받고 할 수있도록 
//도와주는 역할이 서버(톰캣)다!
//서버의 세팅을 자동으로 해주는 역할이 바로 springboot 이다

//spring 
//- 자바 웹/앱을 개발할때 편하게 도와주는 도구!
//- 좋은 이유가 ! 코드를 아무데나 짜지 않고, 정해진 자리에 정리해서
//짜게 도와주는 도구!
//- 자리가 정해져있으니 여러명이 같이 개발해도 헷갈리지 않는다.

//spring 
//- 웹 프로그램을 구조적으로 개발하도록 도와주는것!
//- 서버가 요청을 받으면 어떤 Java코드가 처리할지 연결하고 
// 전체적인 웹 개발을 도와주는 프레임워크

//프레임워크 
//- 프로그램을 만들 때 사용할 큰 틀과 규칙을 미리 만들어 놓은것!

//스프링 프로젝트 구조!
//src/main/java 
//- Java코드만 작성하는 곳!

//MainApplication.java
//- 프로그램의 시작점 
//- 서버 실행!

//src/main/resources
//- 설정파일 등을 놓는곳!(이미지,웹 설정,앱 설정, mysql)
//static - css,javascript,이미지 브라우저에서 사용하는 프론트엔드
//        코드들이 들어간다. 

//pom.xml
//- 필요한 라이브러리 다운로드받는곳!

//Maven Dependncies 
//- 다운 받아온 라이브러리들이 모여있는 폴더!

//위에 처럼 구조를 나눠서 개발자들이 일관되게 프로젝트 설정을 할 수있다.
//개발을 조금 쉽게 할 수있도록 만들어주는 것 (spring)

//구조를 나눠준대서 자동으로 세팅을 도와주는 도구(Springboot)

//윈도우 특성한 대소문자를 구분하지 않아요
//Hello.html,hello.html 같은 파일로 취급해서 준다.

//실무는 윈도우창 안씀..
//리눅스 운영체제를 이용해서 서버에 배포하기때문에!
//리눅스 운영체제는 대소문 구별합니다! 
//Hello.html-> hello.html, HELLO.HTML
//404 Not Found

//파일명,URL경로, 처음부터 대소문자를 정확히 맞춰서 쓰는
//습관!