package com.yonsai.Day68.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
쇼핑몰 사이트 
로그인이 필요한 사이트 / 로그인이 필요하지 않는 사이트 

/            -> 메인페이지 (로그인 없이 OK)
/signup      -> 회원가입 (로그인 없이 OK)
/login       -> 로그인 페이지( 로그인 없이 OK)
/mypage      -> 마이페이지 (로그인 필수! <- anyRequest())
/admin       -> 관리자 페이지 (로그인 필수! <- anyRequest())
/products    -> 상품목록 (로그인 없이 OK)
/cart        -> 장바구니 (로그인 없이/로그인 필수!)
/orders      -> 주문내역 (로그인 필수! <- anyRequest())
*/

@Controller
public class MemberController {

    @GetMapping("/")
    public String main() {
        System.out.println("MemberController - main");
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        System.out.println("MemberController - signup");
        return "signup";
    }

    @GetMapping("/products")
    public String products() {
        System.out.println("MemberController - products");
        return "products";
    }
}
