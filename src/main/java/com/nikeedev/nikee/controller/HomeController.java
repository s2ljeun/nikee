package com.nikeedev.nikee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
public class HomeController {
	// Iamport
	private IamportClient iamportClient;
	private IamportAPI iamportApi;
	
	public HomeController(IamportAPI api) {
		this.iamportApi = api;
		String IAMPORT_API = api.getApi();
		String IAMPORT_API_SECRET = api.getApiSecret();
		this.iamportClient = new IamportClient(IAMPORT_API,IAMPORT_API_SECRET);
	}
	
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
		
		// ?????? ??? ??????????????? ??????, ?????????, ????????? ?????????
		for(ProductDTO pdto : plist) {
			total_cost += pdto.getProd_cost();
			total_price += pdto.getProd_price();
			total_income += pdto.getProd_income();
			order_products += pdto.getProd_name() + ",";
		}
		
		// orderDTO ????????? ??????
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
			// orderDTO??? ?????? no??? ????????????
			OrderDTO odto2 = orderMapper.getOrderLast(mdto.getMem_no());
			int order_no = odto2.getOrder_no();
			// oderDTO ????????? ajax??? ?????????
			map.put("no", order_no);
			map.put("products", odto2.getOrder_products());
			map.put("name", odto2.getOrder_name());
			map.put("price", odto2.getOrder_price());
			map.put("addr", odto2.getOrder_addr());
			//session.removeAttribute("cart");
		} else {
			map.put("cnt", 0);
			map.put("msg", "????????? ??????????????????. ?????? ??????????????????.");
		}
		
		return map;
		
	}
	
	@PostMapping("/payment/verify/{imp_uid}")
	@ResponseBody
	public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale, HttpSession session
									, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException{
		
		return iamportClient.paymentByImpUid(imp_uid);
	}
	
	@PostMapping("/payment/succeed")
	@ResponseBody
	public Map<Object, Object> updateStatus(HttpServletRequest req){
		
		String imp_uid = req.getParameter("imp_uid");
		int order_no = Integer.parseInt(req.getParameter("merchant_uid"));
		int status = 1;
		
		Map<Object, Object> map = new HashMap<>();

		//????????????, ??????????????????, ??????????????? ????????? ????????????
		int res = orderMapper.updateStatus(order_no, imp_uid, status);
		if (res > 0) {
			map.put("cnt", 1);
		}else {
			map.put("cnt", 0);
		}
		
		//???????????? ?????????
		HttpSession session = req.getSession();
		session.removeAttribute("cart");
		
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
		
		// ?????? ??????
		pdto.setProd_amount(prod_amount);

		// ??? ??????, ?????????, ?????? ?????? ??? ??????
		pdto.setProd_cost(pdto.getProd_cost()*prod_amount);
		pdto.setProd_price(pdto.getProd_price() * prod_amount);
		pdto.setProd_income(pdto.getProd_price()*prod_amount - pdto.getProd_cost());
		
		HttpSession session = req.getSession();
		List<ProductDTO> cart;
		if(session.getAttribute("cart") == null) {
			cart = new ArrayList<>();
		}else {
			cart = (List<ProductDTO>) session.getAttribute("cart");
		}
		
		for(ProductDTO dto : cart) {
			// ????????? ?????? ????????? ???????????????
			if(dto.getProd_no() == prod_no) {
				dto.setProd_amount(dto.getProd_amount() + prod_amount); // ?????? ??????
				dto.setProd_cost(dto.getProd_cost() + pdto.getProd_cost()); // ?????? ??????
				dto.setProd_price(dto.getProd_price() + pdto.getProd_price()); // ????????? ??????
				
				session.setAttribute("cart", cart);
				
				req.setAttribute("msg", "??????????????? ?????????????????????.");
				req.setAttribute("url", "/index");
				
				return "message";
			}
		}
		cart.add(pdto);
		session.setAttribute("cart", cart);
		
		req.setAttribute("msg", "??????????????? ?????????????????????.");
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
