<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--method - 방식 / action - servlet의 매핑 주소
		name - 정보 연결 / value - 단순 값 -->
	<form method="get" action="Method">
		<input name="user_name" value="jjang051-get">
		<button>GET</button>
	</form>
	<form method="post" action="Method">
		<input name="user_name" value="jjang051-post">
		<button>POST</button>
	</form>
</body>
</html>