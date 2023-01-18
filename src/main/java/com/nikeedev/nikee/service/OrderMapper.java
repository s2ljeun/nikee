package com.nikeedev.nikee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikeedev.nikee.dto.OrderDTO;

@Service
public class OrderMapper {
	@Autowired
	private SqlSession sqlSession;

	public int insertOrder(OrderDTO odto) {
		return sqlSession.insert("insertOrder", odto);
	}

	public List<OrderDTO> getOrderListByNo(int mem_no) {
		return sqlSession.selectList("getOrderListByNo", mem_no);
	}

	public List<OrderDTO> getAllOrderList() {
		return sqlSession.selectList("getAllOrderList");
	}

	public OrderDTO getOrderLast(int mem_no) {
		return sqlSession.selectOne("getOrderLast", mem_no);
	}

	public int updateStatus(int order_no, int status) {
		Map<String, Integer> map = new HashMap<>();
		map.put("order_no", order_no);
		map.put("status", status);
		return sqlSession.update("updateStatus", map);
	}

}
