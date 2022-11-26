<%@page import="com.jjang051.common.DBConnectionPool"%>
<%@page import="com.jjang051.common.JDBCConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	JDBCConnection dbConnection = new JDBCConnection();
	dbConnection.close();

	String driver = application.getInitParameter("OracleDriver");
	String url = application.getInitParameter("OracleURL");
	String id = application.getInitParameter("OracleId");
	String pw = application.getInitParameter("OraclePassword");

	JDBCConnection dbConnection02 = new JDBCConnection(driver, url, id, pw);
	dbConnection02.close();

	JDBCConnection dbConnection03 = new JDBCConnection(application);
	dbConnection03.close();

	DBConnectionPool dbPool = new DBConnectionPool();
	dbPool.close();
	%>
</body>
</html>