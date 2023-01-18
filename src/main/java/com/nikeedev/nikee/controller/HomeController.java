package com.nikeedev.nikee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nikeedev.nikee.dto.MemberDTO;
import com.nikeedev.nikee.dto.OrderDTO;
import com.nikeedev.nikee.dto.ProductDTO;
import com.nikeedev.nikee.service.MemberMapper;
import com.nikeedev.nikee.service.OrderMapper;
import com.nikeedev.nikee.service.ProductMapper;

@Controller
public class HomeController {
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@GetMapping(value = {"/", "/index"})
	public String goHome(HttpServletRequest req) {
		List<ProductDTO> plist = productMapper.listAllProduct();
		req.setAttribute("allProdList", plist);
		
		return "index";
	}
	
	@GetMapping("/products/{prod_no}")
	public String goProductView(HttpServletRequest req, @PathVariable("prod_no") int prod_no) {
		ProductDTO pdto = productMapper.getProductByNo(prod_no);
		req.setAttribute("pdto", pdto);
		
		return "product";
	}
	
	@GetMapping("/payment")
	public String goPayment(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("userDetail") == null) {
			return "login";
		}
		
		return "payment";
	}
	
	@PostMapping("/payment/proceed")
	public String paymentOk(HttpServletRequest req, @ModelAttribute OrderDTO odto) {
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("userDetail");
		MemberDTO mdto = memberMapper.getMemberById(user.getUsername());
		
		List<ProductDTO> plist = (List<ProductDTO>) session.getAttribute("cart");
		int total_cost = 0;
		int total_price = 0;
		int total_income = 0;
		String order_products = "";
		
		// 카트 안 상품목록의 원가, 판매가, 이익을 더한다
		for(ProductDTO pdto : plist) {
			total_cost += pdto.getProd_cost();
			total_price += pdto.getProd_price();
			total_income += pdto.getProd_income();
			order_products += pdto.getProd_name() + ",";
		}
		
		// orderDTO 내용을 완성
		odto.setMem_no(mdto.getMem_no());
		odto.setOrder_cost(total_cost);
		odto.setOrder_price(total_price);
		odto.setOrder_income(total_income);
		odto.setOrder_products(order_products);
		
		int res = orderMapper.insertOrder(odto);
		if (res > 0) {
			req.setAttribute("msg", "주문이 완료되었습니다.");
			req.setAttribute("url", "/mypage/order");
			session.removeAttribute("cart");
		} else {
			req.setAttribute("msg", "주문을 실패했습니다.");
			req.setAttribute("url", "/index");
		}
		return "message";
	}
	
	@GetMapping("/cart")
	public String goCart() {
		return "cart";
	}
	
	@PostMapping("/cart")
	public String insertCart(HttpServletRequest req, @RequestParam Map<String,String> map) {
		
		int prod_amount = Integer.parseInt(map.get("prod_amount"));
		int prod_no = Integer.parseInt(map.get("prod_no"));
		
		ProductDTO pdto = productMapper.getProductByNo(prod_no);	
		
		// 갯수 세팅
		pdto.setProd_amount(Integer.parseInt(map.get("prod_amount")));

		// 총 원가, 이익 계산 후 세팅
		pdto.setProd_cost(pdto.getProd_cost()*prod_amount);
		pdto.setProd_income(pdto.getProd_price()*prod_amount - pdto.getProd_cost());
		
		HttpSession session = req.getSession();
		List<ProductDTO> cart;
		if(session.getAttribute("cart") == null) {
			cart = new ArrayList<>();
		}else {
			cart = (List<ProductDTO>) session.getAttribute("cart");
		}
		
		cart.add(pdto);
		session.setAttribute("cart", cart);
		
		req.setAttribute("msg", "장바구니에 추가되었습니다.");
		req.setAttribute("url", "/index");
		
		return "message";
	}
}
