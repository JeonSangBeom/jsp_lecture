<%@page import="com.jjang051.util.ScriptWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.jjang051.jdbc.JDBCConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	request.setCharacterEncoding("UTF-8");
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
	
	JDBCConnection jdbc = new JDBCConnection();
	Connection conn = jdbc.conn;	
	ResultSet rs = null;
	
	String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PASSWORD = ?";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, user_id);
	pstmt.setString(2, user_pw);
	rs = pstmt.executeQuery();
	if(rs.next()) { //로그인 할땐 session 값 담아두기(다른 곳 활용 index / heaeder 등등)
		session.setAttribute("name", rs.getString("name"));
		session.setAttribute("id", rs.getString("id"));
		response.sendRedirect("/chap06_member_review");
	} else {
		ScriptWriter.alertAndBack(response,"아이디 패스워드를 확인해 주세요");
	}
	jdbc.close();
%>

