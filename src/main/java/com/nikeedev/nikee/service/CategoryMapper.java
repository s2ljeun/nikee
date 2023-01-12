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

	public List<CategoryDTO> listAllCategory() {
		return sqlSession.selectList("listAllCategory");
	}

	public CategoryDTO getCategoryByNo(int cate_no) {
		return sqlSession.selectOne("getCategoryByNo", cate_no);
	}

	public int updateCategory(CategoryDTO cdto) {
		return sqlSession.update("updateCategory", cdto);
	}

	public int deleteCategory(int cate_no) {
		return sqlSession.delete("deleteCategory", cate_no);
	}
}
