<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% // 로그아웃 클릭시 세션 날리기
	session.invalidate(); // 세션 강제로 지우기
	response.sendRedirect("main.jsp");// 경고창 안띄우고 바로 이동하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04</title>
</head>
<body>

</body>
</html>