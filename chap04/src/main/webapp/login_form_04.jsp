<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 시작 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here01</title>
</head>
<body>
	<form action="login_ok_04.jsp" method="post">
	<!-- 로그인은 보통 post로 많이 보낸다 -->
		<div><label>아이디<input type="text"		name="user_id"></label></div>
		<div><label>패스워드<input type="password" 		name="user_pw"></label></div>
		<button>로그인</button>
	</form>
</body>
</html>