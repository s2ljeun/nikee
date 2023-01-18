package com.nikeedev.nikee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nikeedev.nikee.dto.MemberDTO;
import com.nikeedev.nikee.dto.OrderDTO;
import com.nikeedev.nikee.service.MemberMapper;
import com.nikeedev.nikee.service.OrderMapper;

@Controller
public class MypageController {
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	@GetMapping("/mypage")
	public String goMypage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("userDetail");
		MemberDTO mdto = memberMapper.getMemberById(user.getUsername());
		req.setAttribute("mdto", mdto);
		return "mypage/index";
	}
	
	@PostMapping("/mypage/update")
	public String updateMypage(HttpServletRequest req, @ModelAttribute MemberDTO mdto) {
		int res = memberMapper.updateMember(mdto);
		if(res > 0) {
			req.setAttribute("msg", "회원정보를 수정했습니다.");
			req.setAttribute("url", "/mypage");
		}else {
			req.setAttribute("msg", "회원정보 수정에 실패했습니다.");
			req.setAttribute("url", "/mypage");
		}
		return "message";
	}
	
	@GetMapping("/mypage/order")
	public String goMypageOrder(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("userDetail");
		MemberDTO mdto = memberMapper.getMemberById(user.getUsername());
		int mem_no = mdto.getMem_no();
		
		List<OrderDTO> olist = orderMapper.getOrderListByNo(mem_no);
		req.setAttribute("orderList", olist);
		return "mypage/order";
	}

}
