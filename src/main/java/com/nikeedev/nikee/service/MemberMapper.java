package com.nikeedev.nikee.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikeedev.nikee.dto.MemberDTO;

@Service
public class MemberMapper {
	@Autowired
	private SqlSession sqlSession;
	
	public List<MemberDTO> listAllMember() {
		return sqlSession.selectList("listAllMember");
	}

	public MemberDTO getMemberByNo(int mem_no) {
		return sqlSession.selectOne("getMemberByNo", mem_no);
	}

	public MemberDTO getMemberById(String insertedId) {
		return sqlSession.selectOne("getMemberById", insertedId);
	}
	
	public int updateMember(MemberDTO mdto) {
		return sqlSession.update("updateMember", mdto);
	}

	public int insertMember(MemberDTO mdto) {
		return sqlSession.insert("insertMember", mdto);
	}
	

}
