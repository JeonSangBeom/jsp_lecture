<%@page import="com.jjang051.util.ScriptWriter"%>
<%@page import="com.jjang051.model.MemberDao"%>
<%@page import="com.jjang051.model.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	MemberDto memberDto =  new MemberDto(); // 정의해둔 두개 dto dao 생성자 생성
	MemberDao memberDao = new MemberDao();
	
	String user_phone_first = request.getParameter("user_phone_first");
	String user_phone_middle = request.getParameter("user_phone_middle");
	String user_phone_last = request.getParameter("user_phone_last");
	String user_phone = user_phone_first+"-"+user_phone_middle+"-"+user_phone_last;
	String user_address = "";
	String address01 = request.getParameter("address01");
	String address02 = request.getParameter("address02");
	user_address = address01+address02;
	
	
	//get the set 으로 잡아둔 것 가져와서 Parameter에 넣어주기 (name으로 받아온 것)
	memberDto.setId(request.getParameter("user_id"));  
	memberDto.setName(request.getParameter("user_name"));  
	memberDto.setPassword(request.getParameter("user_pw"));  
	memberDto.setEmail(request.getParameter("user_email"));  
	memberDto.setPhone(user_phone);
	memberDto.setZipCode(Integer.parseInt( request.getParameter("zipcode") ));
	memberDto.setAddress(user_address);
	
	System.out.print(memberDto); // dto테스트
	
	int result = memberDao.insertMember(memberDto);// dto로 한꺼번에 받는 것
	if(result>0) {
		ScriptWriter.alertAndNext(response, "회원가입이 되었습니다.", "/chap08_member_dao");
	} else {
		ScriptWriter.alertAndBack(response, "회원가입에 실패하였습니다.");
	}
%>
