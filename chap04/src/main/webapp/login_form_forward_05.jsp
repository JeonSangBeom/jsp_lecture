<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- sendRedirect 보다 더 많이 사용하는 방법
    	내부적으로 넘길때 주로 많이 사용 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here01</title>
</head>
<body>
	<form action="login_ok_forward_05.jsp" method="post">
		<div><label>나이<input type="text"		name="user_age"></label></div>
		<button>미성년자 입장 금지</button>
	</form>
</body>
</html>