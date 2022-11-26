<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_id = request.getParameter("user_id"); // 넘어오는 것은 무조건 string
	String user_pw = request.getParameter("user_pw"); // 넘어오는 것은 무조건 string
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= user_id %><br>
	<%= user_pw %><br> <!--  넘어온 값을 찍어준다(입력한 아이디 비번) -->
	${param.user_id }<br> <!-- param 을 이용하면 바로 값이 넘어오게 사용할 수 있다 -->
	${param.user_id == "jjang051" }<br><!-- 값 비교 true나 false -->
	${param.user_pw == 1234 }<br> <!-- 숫자를 데이터로 넘기면 알아서 형 변환을 해준다 -->

</body>
</html>