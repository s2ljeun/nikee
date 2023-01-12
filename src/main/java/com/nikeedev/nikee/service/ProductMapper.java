package com.nikeedev.nikee.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikeedev.nikee.dto.ProductDTO;

@Service
public class ProductMapper {
	@Autowired
	private SqlSession sqlSession;

	public int insertProduct(ProductDTO pdto) {
		return sqlSession.insert("insertProduct", pdto);
	}
}
