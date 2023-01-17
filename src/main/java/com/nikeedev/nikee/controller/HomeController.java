package com.nikeedev.nikee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
		List<ProductDTO> plist = productMapper.listAllProduct();
		req.setAttribute("allProdList", plist);
		
		return "index";
	}
}
