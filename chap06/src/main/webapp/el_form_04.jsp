<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="el_form_read_04.jsp" method="get">
		<div><input type="text" name="user_id"></div>
		<div><input type="password" name="user_pw"></div>
		<div>
			<label><span>낚시</span><input type="checkbox" name="favorite" value="낚시"></label>
			<label><span>영화감상</span><input type="checkbox" name="favorite" value="영화감상"></label>
			<label><span>골프</span><input type="checkbox" name="favorite" value="골프"></label>
			<label><span>게임</span><input type="checkbox" name="favorite" value="게임"></label>
			<label><span>자전거</span><input type="checkbox" name="favorite" value="자전거"></label>
		</div>
		<button>전송</button>		
		
	</form>
</body>
</html>