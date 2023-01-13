package com.nikeedev.nikee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {
	@GetMapping("/mypage")
	public String goMypage(HttpServletRequest req) {
		return "mypage/index";
	}
	
	@GetMapping("/mypage/order")
	public String goMypageOrder(HttpServletRequest req) {
		return "mypage/order";
	}

}
