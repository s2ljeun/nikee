package com.nikeedev.nikee.service;

import java.util.List;

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

}
