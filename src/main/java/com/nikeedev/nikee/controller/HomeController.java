package com.nikeedev.nikee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nikeedev.nikee.dto.ProductDTO;
import com.nikeedev.nikee.service.ProductMapper;

@Controller
public class HomeController {
	@Autowired
	private ProductMapper productMapper;
	
	@GetMapping(value = {"/", "/index"})
	public String goHome(HttpServletRequest req) {
		List<ProductDTO> plist = productMapper.listAllProduct();
		req.setAttribute("allProdList", plist);
		
		return "index";
	}
	
	@GetMapping("/{prod_no}")
	public String goProductView(HttpServletRequest req, @PathVariable("prod_no") int prod_no) {
		ProductDTO pdto = productMapper.getProductByNo(prod_no);
		req.setAttribute("pdto", pdto);
		
		return "product";
	}
	
	@PostMapping("/payment")
	public String goPayment(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("userDetail") == null) {
			return "login";
		}
		return "payment";
	}
	
	@GetMapping("/cart")
	public String goCart() {
		return null;
	}
}
