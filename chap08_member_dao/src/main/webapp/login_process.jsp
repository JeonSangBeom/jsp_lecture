<%@page import="com.jjang051.model.MemberDao"%>
<%@page import="com.jjang051.model.MemberDto"%>
<%@page import="com.jjang051.util.ScriptWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.jjang051.jdbc.JDBCConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	//새로 작성할 부분
	MemberDto memberDto = new MemberDto();
	memberDto.setId(request.getParameter("user_id"));//넘어온 아이디 담아주기
	memberDto.setPassword(request.getParameter("user_pw"));
	MemberDao memberDao = new MemberDao();
	MemberDto loggedMember = memberDao.getLoggedMember(memberDto); // 위에 dto에서 넘겨준 id와 pw를 dao에서 get받아왔다 
	if(loggedMember!=null) {
		//로그인이 된거
		session.setAttribute("loggedId", loggedMember.getId());
		session.setAttribute("loggedName", loggedMember.getName());
		session.setAttribute("loggedMember", loggedMember);
		// 하나씩 필요할땐 위 id와 name 처럼 담아사용하면 되고 한꺼번에 필요할 경우 Member로 한꺼번에 사용하여도 된다
		// 로그인 한는 순간에 session에 값이 담긴다(session은 내가 종료하기 전까지는 계속 돌아)
		ScriptWriter.alertAndNext(response, loggedMember.getName()+"님 안녕하세요.", "/chap08_member_dao");
	} else {
		//로그인 실패...
		ScriptWriter.alertAndBack(response, "아이디 비밀번호 확인해 주세요.");
	}
%>






