<%@page import="com.jjang051.util.ScriptWriter"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.jjang051.jdbc.JDBCConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
	
	JDBCConnection jdbcConnection = new JDBCConnection();
	PreparedStatement pstmt = jdbcConnection.pstmt;
	Connection conn = jdbcConnection.conn;
	
	String sql = "DELETE FROM MEMBER WHERE ID = ? AND PASSWORD = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, user_id);
	pstmt.setString(2, user_pw);
	int result = pstmt.executeUpdate();
	if(result>0) {
		ScriptWriter.alertAndNext(response, "회원탈되 되었습니다.", "/chap06_member_review");
		session.invalidate();
	} else {
		ScriptWriter.alertAndBack(response, "아이디 패스워드 확인 해주세요");
	}
%>

   