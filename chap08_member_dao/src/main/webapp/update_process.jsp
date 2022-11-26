<%@page import="com.jjang051.model.MemberDao"%>
<%@page import="com.jjang051.model.MemberDto"%>
<%@page import="com.jjang051.util.ScriptWriter"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.jjang051.jdbc.JDBCConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
	String user_name = request.getParameter("user_name");
	String user_email = request.getParameter("user_email");
	String user_phone = "";
	String user_phone_first = request.getParameter("user_phone_first");
	String user_phone_middle = request.getParameter("user_phone_middle");
	String user_phone_last = request.getParameter("user_phone_last");
	user_phone = user_phone_first+"-"+user_phone_middle+"-"+user_phone_last;
	int user_zipcode = Integer.parseInt( request.getParameter("zipcode") );
	String user_address = "";
	String address01 = request.getParameter("address01");
	String address02 = request.getParameter("address02");
	user_address = address01+address02;
	// 넘어온 값들은 두고 
	
	// 우선 선언
	MemberDto memberDto = new MemberDto();
	MemberDao memberDao = new MemberDao();
	// 위에 넘어온 값 받아오기
	memberDto.setId(user_id); 
	memberDto.setName(user_name);
	memberDto.setZipCode(user_zipcode);
	memberDto.setAddress(user_address);
	memberDto.setPassword(user_pw);
	memberDto.setPhone(user_phone);
	memberDto.setEmail(user_email);
	
	int result = memberDao.updateMember(memberDto); //memberDao - db 왔다갔다 하는 것
	// int를 리턴하니까 리턴한 값을 result에 담아두고 아래 조건 실행
	
	if(result > 0) {
		session.setAttribute("loggedName", user_name);
		ScriptWriter.alertAndNext(response, "회원정보가 수정되었습니다.", "/chap08_member_dao");
	} else {
		ScriptWriter.alertAndBack(response, "비밀번호를 확인해 주세요.");
	}
%>
