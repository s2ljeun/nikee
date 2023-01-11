package com.nikeedev.nikee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nikeedev.nikee.dto.MemberDTO;
import com.nikeedev.nikee.login.LoginIdPwValidator;
import com.nikeedev.nikee.service.MemberMapper;

@Controller
public class MemberController {
	@Autowired
    private MemberMapper memberMapper;
	@Autowired
	private LoginIdPwValidator loginIdPwValidator;
	
	@GetMapping("/join")
	public String goJoin() {
		return "join";
	}
	
	@PostMapping("/join")
	public String joinOk(HttpServletRequest req, @ModelAttribute MemberDTO mdto) {
		//패스워드 암호화
		String encodedPasswd = loginIdPwValidator.passwordEncoder().encode(mdto.getMem_passwd());
		mdto.setMem_passwd(encodedPasswd);
		
		int res = memberMapper.insertMember(mdto);
		if(res > 0) {
			req.setAttribute("msg", "회원가입이 완료되었습니다.");
			req.setAttribute("url", "/index");
		}else {
			req.setAttribute("msg", "회원가입에 실패했습니다.");
			req.setAttribute("url", "/join");
		}
		
		return "message";
	}
}
