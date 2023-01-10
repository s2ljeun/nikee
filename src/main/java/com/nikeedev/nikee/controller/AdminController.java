package com.nikeedev.nikee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nikeedev.nikee.dto.MemberDTO;
import com.nikeedev.nikee.service.MemberMapper;

@Controller
public class AdminController {
	@Autowired
    private MemberMapper memberMapper;
	
	@GetMapping("/admin")
	public String goAdmin() {
		return "admin/index";
	}
	
	@GetMapping("/products")
	public String goProductList() {
		return "admin/product_list";
	}
	
	@GetMapping("/order")
	public String goOrder() {
		return "admin/order";
	}
	
	@GetMapping("/members")
	public String goMemberList(HttpSession session) {
		if (session.getAttribute("allMemberList") == null) {
			List<MemberDTO> list = memberMapper.listAllMember();
			session.setAttribute("allMemberList", list);
		}
		
		return "admin/member_list";
	}
	
	@GetMapping("/members/edit/{mem_no}")
	public String putMember(HttpServletRequest req, @PathVariable("mem_no") int mem_no) {
		MemberDTO mdto = memberMapper.getMemberByNo(mem_no);
		req.setAttribute("mdto", mdto);
		return "admin/member_view";
	}
	
	@PostMapping("/members/edit")
	public String putMemberOk(HttpServletRequest req, @ModelAttribute MemberDTO mdto) {
		int res = memberMapper.updateMember(mdto);
		req.setAttribute("mdto", mdto);
		req.setAttribute("msg", "회원 수정이 완료되었습니다.");
		req.setAttribute("url", "/members/edit/"+mdto.getMem_no());
		return "message";
	}
	
	@GetMapping("/members/delete/{mem_no}")
	public String delMember(HttpServletRequest req, @PathVariable("mem_no") int mem_no) {
		req.setAttribute("msg", "회원 삭제가 완료되었습니다.");
		req.setAttribute("url", "/members");
		return "message";
	}
	
	@GetMapping("/report")
	public String goReport() {
		return "admin/report";
	}
}
