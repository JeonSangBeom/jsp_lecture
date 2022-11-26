<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Params</title>
</head>
<body>
	<form action="ParamServlet" method="get"> 
	 <!-- action:어디서 처리할건지(실행할 곳)  method:get이나 post 입력(대소문자 구분 안함) -->
		<div><label>아이디 : <input type="text" name="user_id" placeholder="아이디를 입력하세요."></label></div>
		<div><label>나이 : <input type="text" name="user_age" placeholder="나이를 입력하세요."></label></div>
		<!-- <input type="submit" value="전송"> -- 버튼을 안만들경우 이런 방식으로 사용한다(value는 필수) - 또 다른 버튼 만드는 법 -->
		<button>전송</button>
	</form>
	<form action="ParamServlet" method="post">
		<div><label>아이디 : <input type="text" name="user_id" placeholder="아이디를 입력하세요."></label></div>
		<div><label>나이 : <input type="text" name="user_age" placeholder="나이를 입력하세요."></label></div>
		<!-- <input type="submit" value="전송">   -->
		<button>전송</button>
	</form>
</body>
</html>