<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//id session 메인 페이지
	String login_user=""; // 공백 세팅(처음에)
	login_user = (String) session.getAttribute("login_user"); //데이터 읽기(값이 세팅 되어 있으면 값을 받아오기)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01</title>
</head>
<body>
	<h1>세션 연습</h1>
	<%if(login_user =="" || login_user == null){ // 로그인 유저가 공백이나 널이면 %>
		<a href="id_save_session_05.jsp">로그인</a> <!-- 클릭시 아이디 저장하기의 유무는 쿠키를 지우는지 안지우는지로 나뉜다 / 세션과 별개 -->
	<%} else { %>
		<a href="user_info.jsp"><%= login_user %>님 반갑습니다</a>
		<a href="logout.jsp">로그아웃</a>
	<%} %>
</body>
</html>