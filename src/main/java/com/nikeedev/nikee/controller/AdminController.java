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

import com.nikeedev.nikee.dto.CategoryDTO;
import com.nikeedev.nikee.dto.MemberDTO;
import com.nikeedev.nikee.service.CategoryMapper;
import com.nikeedev.nikee.service.MemberMapper;

@Controller
public class AdminController {
	@Autowired
    private MemberMapper memberMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@GetMapping("/admin")
	public String goAdmin() {
		return "admin/index";
	}
	
	//카테고리	
	@GetMapping("/category")
	public String goCategory(HttpServletRequest req) {
		List<CategoryDTO> list = categoryMapper.getAllCategory();
		req.setAttribute("allCateList", list);
		return "admin/category_list";
	}
	
	@GetMapping("/category/insert")
	public String goInsertCategory() {
		return "admin/category_insert";
	}
	
	@PostMapping("/category/insert")
	public String insertCategory(HttpServletRequest req, @ModelAttribute CategoryDTO cdto) {
		int res = categoryMapper.insertCategory(cdto);
		if(res > 0) {
			req.setAttribute("msg", "카테고리 추가가 완료되었습니다.");
			req.setAttribute("url", "/category");
		}else {
			req.setAttribute("msg", "카테고리 추가를 실패했습니다.");
			req.setAttribute("url", "/category");
		}
		
		return "message";
	}
	
	@GetMapping("/category/update/{cate_no}")
	public String goUpdateCategory(HttpServletRequest req, @PathVariable("cate_no") int cate_no) {
		CategoryDTO cdto = categoryMapper.getCategoryByNo(cate_no);
		req.setAttribute("cdto", cdto);
		return "admin/category_update";
	}
	
	@PostMapping("/category/update")
	public String updateCategory(HttpServletRequest req, @ModelAttribute CategoryDTO cdto) {
		int res = categoryMapper.updateCategory(cdto);
		if(res > 0) {
			req.setAttribute("msg", "카테고리 수정이 완료되었습니다.");
			req.setAttribute("url", "/category");
		}else {
			req.setAttribute("msg", "카테고리 수정을 실패했습니다.");
			req.setAttribute("url", "/category");
		}
		
		return "message";
	}
	
	@GetMapping("/category/delete/{cate_no}")
	public String delCategory(HttpServletRequest req, @PathVariable("cate_no") int cate_no) {
		int res = categoryMapper.deleteCategory(cate_no);
		if(res>0) {
			req.setAttribute("msg", "카테고리 삭제가 완료되었습니다.");
			req.setAttribute("url", "/category");
		}else {
			req.setAttribute("msg", "카테고리 삭제를 실패했습니다.");
			req.setAttribute("url", "/category");
		}
		return "message";
	}
	
	//상품	
	@GetMapping("/products")
	public String goProductList() {
		return "admin/product_list";
	}
	
	@GetMapping("/products/insert")
	public String goProductInsert() {
		return "admin/product_insert";
	}
	
	@GetMapping("/order")
	public String goOrder() {
		return "admin/order";
	}
	
	@GetMapping("/members")
	public String goMemberList(HttpServletRequest req) {
		List<MemberDTO> list = memberMapper.listAllMember();
		req.setAttribute("allMemberList", list);
		
		return "admin/member_list";
	}
	
	@GetMapping("/members/edit/{mem_no}")
	public String putMember(HttpServletRequest req, @PathVariable("mem_no") int mem_no) {
		MemberDTO mdto = memberMapper.getMemberByNo(mem_no);
		req.setAttribute("mdto", mdto);
		return "admin/member_view";
	}
	
	@PostMapping("/members/edit")
	public String putMemberOk(HttpServletRequest req, HttpSession session, @ModelAttribute MemberDTO mdto) {
		int res = memberMapper.updateMember(mdto);
		if(res>0) {
			req.setAttribute("mdto", mdto);
			req.setAttribute("msg", "회원 수정이 완료되었습니다.");
			req.setAttribute("url", "/members/edit/"+mdto.getMem_no());		
		}else {
			req.setAttribute("msg", "회원 수정을 실패했습니다.");
			req.setAttribute("url", "/members/edit/"+mdto.getMem_no());	
		}
		
		return "message";
	}
	
	@PostMapping("/members/delete/{mem_no}")
	public String delMember(HttpServletRequest req, @PathVariable("mem_no") int mem_no) {
		int res = memberMapper.deleteMember(mem_no);
		if(res>0) {
			req.setAttribute("msg", "회원 삭제가 완료되었습니다.");
			req.setAttribute("url", "/members");
		}else {
			req.setAttribute("msg", "회원 삭제를 실패했습니다.");
			req.setAttribute("url", "/members");
		}
		return "message";
	}
	
	@GetMapping("/report")
	public String goReport() {
		return "admin/report";
	}
	
}
