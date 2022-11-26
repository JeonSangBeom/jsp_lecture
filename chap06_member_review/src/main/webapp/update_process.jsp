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
		
	JDBCConnection jdbc = new JDBCConnection();
	PreparedStatement pstmt = jdbc.pstmt;
	Connection conn = jdbc.conn;

	String sql =  "UPDATE MEMBER SET NAME = ?, EMAIL = ?, PHONE = ?, ZIPCODE = ?,ADDRESS = ? WHERE ID = ? AND PASSWORD = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, user_name);
	pstmt.setString(2,user_email);
	pstmt.setString(3,user_phone);
	pstmt.setInt(4, user_zipcode);
	pstmt.setString(5,user_address);
	pstmt.setString(6,user_id);
	pstmt.setString(7,user_pw);
	int result = pstmt.executeUpdate();
	if(result>0) {
		session.setAttribute("name", user_name);
		ScriptWriter.alertAndNext(response, "회원정보가 수정되었습니다.", "/chap06_member_review");
	} else {
		ScriptWriter.alertAndBack(response, "비밀번호를 확인 해주세요");
	}
%>    