package com.jjang051.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MemberDao {
	public List<MemberDto> getAllList() {
		List<MemberDto> memberList = null;
		// 1. 연결 2.마이바티스 메서드 .selectOne("아이디") / .selectList / update / delete / insert
		// 3. select를 제외한 나머지는 commit()
		// 4. close();

		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		memberList = sqlSession.selectList("getAllMember");
		sqlSession.close();
		return memberList;
	}

	public MemberDto getSelectOne(String id) {
		MemberDto memberDto = null;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		memberDto = sqlSession.selectOne("getSelectOne", id);
		sqlSession.close();
		return memberDto;
	}

	public int insertMember(MemberDto memberDto) {
		int result = 0;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		result = sqlSession.insert("insertMember",memberDto);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	public int idCheck(String id) {
		int result = 0;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		result = sqlSession.selectOne("idCheck",id);
		sqlSession.close();
		return result;
	}
	
	public MemberDto getLoggedMember(MemberDto pMemberDto) {
		MemberDto memberDto = null;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		memberDto = sqlSession.selectOne("getLoggedMember",pMemberDto);
		sqlSession.close();
		return memberDto;
	}
	
	public int deleteMember(MemberDto memberDto) {
		int result = 0;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		result = sqlSession.delete("deleteMember",memberDto);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	public int updateMember(MemberDto memberDto) {
		int result = 0;
		SqlSession sqlSession = MybatisConnectionFactory.getSqlSession();
		result = sqlSession.update("updateMember",memberDto);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
}



