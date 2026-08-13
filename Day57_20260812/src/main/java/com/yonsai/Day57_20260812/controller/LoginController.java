package com.yonsai.Day57_20260812.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "Login처리함";
	}
}
