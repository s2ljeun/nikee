package com.nikeedev.nikee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.nikeedev.nikee.dto.ProductDTO;
import com.nikeedev.nikee.service.ProductMapper;

@Controller
public class HomeController {
	@Autowired
	private ProductMapper productMapper;
	
	@GetMapping(value = {"/", "/index"})
	public String goHome(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("allProdList") == null) {
			List<ProductDTO> plist = productMapper.listAllProduct();
			session.setAttribute("allProdList", plist);
		}
		
		String web_path = "/resources/img";
		final String upPath = req.getServletContext().getRealPath(web_path); // 절대경로

		System.out.println("업패스:" + upPath);
		
		req.setAttribute("upPath", upPath);
		
		return "index";
	}
}
