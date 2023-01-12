package com.nikeedev.nikee.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nikeedev.nikee.dto.CategoryDTO;
import com.nikeedev.nikee.dto.MemberDTO;
import com.nikeedev.nikee.dto.ProductDTO;
import com.nikeedev.nikee.service.CategoryMapper;
import com.nikeedev.nikee.service.MemberMapper;
import com.nikeedev.nikee.service.ProductMapper;

@Controller
public class AdminController {
	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private ProductMapper productMapper;

	@GetMapping("/admin")
	public String goAdmin() {
		return "admin/index";
	}

	// 카테고리
	@GetMapping("/category")
	public String goCategory(HttpServletRequest req) {
		List<CategoryDTO> list = categoryMapper.listAllCategory();
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
		if (res > 0) {
			req.setAttribute("msg", "카테고리 추가가 완료되었습니다.");
			req.setAttribute("url", "/category");
		} else {
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
		if (res > 0) {
			req.setAttribute("msg", "카테고리 수정이 완료되었습니다.");
			req.setAttribute("url", "/category");
		} else {
			req.setAttribute("msg", "카테고리 수정을 실패했습니다.");
			req.setAttribute("url", "/category");
		}

		return "message";
	}

	@GetMapping("/category/delete/{cate_no}")
	public String delCategory(HttpServletRequest req, @PathVariable("cate_no") int cate_no) {
		int res = categoryMapper.deleteCategory(cate_no);
		if (res > 0) {
			req.setAttribute("msg", "카테고리 삭제가 완료되었습니다.");
			req.setAttribute("url", "/category");
		} else {
			req.setAttribute("msg", "카테고리 삭제를 실패했습니다.");
			req.setAttribute("url", "/category");
		}
		return "message";
	}

	// 상품
	@GetMapping("/products")
	public String goProductList(HttpServletRequest req) {
		List<ProductDTO> plist = productMapper.listAllProduct();
		req.setAttribute("plist", plist);
		
		return "admin/product_list";
	}

	@GetMapping("/products/insert")
	public String goInsertProduct(HttpServletRequest req) {
		List<CategoryDTO> list = categoryMapper.listAllCategory();
		req.setAttribute("clist", list);

		return "admin/product_insert";
	}

	@PostMapping("/products/insert")
	public String insertProduct(HttpServletRequest req, @ModelAttribute ProductDTO pdto, BindingResult result) {

		if (result.hasErrors()) {
			pdto.setProd_img("");
		}

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		MultipartFile file = mr.getFile("prod_img");
		String file_name = file.getOriginalFilename();

		// UUID
		String uuid = UUID.randomUUID().toString();
		file_name = uuid + '_' + file_name;
		File target = new File(file_name);
		try {
			file.transferTo(target);
		} catch (IOException e) {
			e.printStackTrace();
			req.setAttribute("msg", "이미지 업로드 중 오류 발생, 다시 등록해주세요");
			req.setAttribute("url", "products");
			return "message";
		}

		//System.out.println(target); // 파일객체
		//System.out.println(file); // 멀티파트 파일객체
		
		pdto.setProd_img(file_name);
		
		int res = productMapper.insertProduct(pdto);
		if (res > 0) {
			req.setAttribute("msg", "상품 등록이 완료되었습니다.");
			req.setAttribute("url", "/products");
		} else {
			req.setAttribute("msg", "상품 등록에 실패했습니다.");
			req.setAttribute("url", "/products");
		}
		return "message";
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

	@GetMapping("/members/update/{mem_no}")
	public String putMember(HttpServletRequest req, @PathVariable("mem_no") int mem_no) {
		MemberDTO mdto = memberMapper.getMemberByNo(mem_no);
		req.setAttribute("mdto", mdto);
		return "admin/member_update";
	}

	@PostMapping("/members/update")
	public String putMemberOk(HttpServletRequest req, HttpSession session, @ModelAttribute MemberDTO mdto) {
		int res = memberMapper.updateMember(mdto);
		if (res > 0) {
			req.setAttribute("mdto", mdto);
			req.setAttribute("msg", "회원 수정이 완료되었습니다.");
			req.setAttribute("url", "/members/update/" + mdto.getMem_no());
		} else {
			req.setAttribute("msg", "회원 수정을 실패했습니다.");
			req.setAttribute("url", "/members/update/" + mdto.getMem_no());
		}

		return "message";
	}

	@GetMapping("/members/delete/{mem_no}")
	public String delMember(HttpServletRequest req, @PathVariable("mem_no") int mem_no) {
		int res = memberMapper.deleteMember(mem_no);
		if (res > 0) {
			req.setAttribute("msg", "회원 삭제가 완료되었습니다.");
			req.setAttribute("url", "/members");
		} else {
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
