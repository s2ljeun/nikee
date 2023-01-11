package com.nikeedev.nikee.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikeedev.nikee.dto.CategoryDTO;

@Service
public class CategoryMapper {
	@Autowired
	private SqlSession sqlSession;

	public int insertCategory(CategoryDTO cdto) {
		return sqlSession.insert("insertCategory", cdto);
	}

	public List<CategoryDTO> getAllCategory() {
		return sqlSession.selectList("listAllCategory");
	}
}
