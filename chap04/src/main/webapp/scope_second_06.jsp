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
		System.out.println("scope_second=======");
		System.out.println("pageContext======="+pageContext.getAttribute("name")); // null로 나온다.
		System.out.println("request======="+request.getAttribute("name"));
		System.out.println("session======="+session.getAttribute("name"));
		System.out.println("application======="+application.getAttribute("name"));
	%>
	<a href="scope_third_06.jsp">scope_third로 값 넘겨보기</a>
</body>
</html>