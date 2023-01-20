package com.nikeedev.nikee.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public Map<Object, Object> paymentOk(HttpServletRequest req, @ModelAttribute OrderDTO odto) {
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
		// orderDTO insert
		int res = orderMapper.insertOrder(odto);
		Map<Object, Object> map = new HashMap<>();
		if (res > 0) {
			map.put("cnt", 1);
			// orderDTO의 고유 no값 가져오기
			OrderDTO odto2 = orderMapper.getOrderLast(mdto.getMem_no());
			int order_no = odto2.getOrder_no();
			// oderDTO 내용을 ajax로 넘기기
			map.put("no", order_no);
			map.put("products", odto2.getOrder_products());
			map.put("name", odto2.getOrder_name());
			map.put("price", odto2.getOrder_price());
			map.put("addr", odto2.getOrder_addr());
			//session.removeAttribute("cart");
		} else {
			map.put("cnt", 0);
			map.put("msg", "주문을 실패했습니다. 다시 시도해주세요.");
		}
		
		return map;
		
	}
	
	@PostMapping("/payment/succeed")
	@ResponseBody
	public Map<Object, Object> updateStatus(HttpServletRequest req){
		String imp_uid = req.getParameter("imp_uid");
		int order_no = Integer.parseInt(req.getParameter("merchant_uid"));
		int status = 1;
		
		Map<Object, Object> map = new HashMap<>();

		//주문번호, 결제고유번호, 결제상태를 인자로 넘겨준다
		int res = orderMapper.updateStatus(order_no, imp_uid, status);
		if (res > 0) {
			map.put("cnt", 1);
		}else {
			map.put("cnt", 0);
		}
		return map;
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
	
	@GetMapping("/cart/delete/{prod_no}")
	public String deleteCart(HttpServletRequest req, @PathVariable("prod_no") int prod_no) {
		HttpSession session = req.getSession();
		List<ProductDTO> cart = (List<ProductDTO>) session.getAttribute("cart");
		for(int i=0; i<cart.size(); i++) {
			ProductDTO pdto = cart.get(i);
			if(pdto.getProd_no() == prod_no) {
				cart.remove(i);
			}else {
			}
		}
		session.setAttribute("cart", cart);
		return "cart";
	}
}
