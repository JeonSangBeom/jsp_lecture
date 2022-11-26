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
		// pageContext  (단일 페이지)
		// request      (forward 로 넘긴 페이지)
		// session      (session이 유지되는 페이지 까지...)
		// application  (이거는 전역)
		System.out.println("scope_third=======");
		System.out.println("pageContext======="+pageContext.getAttribute("name"));//null
		System.out.println("request======="+request.getAttribute("name"));//null - forward로 넘기면 값이 오지만 링크 방식은 안넘어온다
		System.out.println("session======="+session.getAttribute("name"));
		System.out.println("application======="+application.getAttribute("name"));
	%>
	<a href="scope_third_06.jsp">새로고침</a>
	<!-- 익스플로어(브라우저)에서 실행하여 새로고침을 할 경우는 session도 null값이 나온다 -->
</body>
</html>