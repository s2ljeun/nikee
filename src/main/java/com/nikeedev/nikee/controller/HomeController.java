package com.nikeedev.nikee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value = {"/", "/index"})
	public String goHome(HttpServletRequest request) {
		return "index";
	}
}
