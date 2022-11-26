package com.jjang051.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class ReplyBoardDao {
	public List<ReplyBoardDto> getAllList() {
		List<ReplyBoardDto> boardList = null;
		
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		boardList = sqlSession.selectList("getAllBoard");
		sqlSession.close();

		return boardList;
	}
	
	public int allDeleteBoard(int no) {
		int result = 0;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		result = sqlSession.delete("allDeleteBoard",no);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	public ReplyBoardDto getSelectOne(int no) {
		ReplyBoardDto boardDto =  null;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		boardDto = sqlSession.selectOne("getSelectOneBoard",no);
		sqlSession.close();
		return boardDto; 
	}
}
