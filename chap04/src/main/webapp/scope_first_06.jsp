<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here01</title>
</head>
<body>
	<%
		// pageContext  (단일 페이지)
		// request      (forward 로 넘긴 페이지 - 2개)
		// session      (session이 유지되는 페이지 까지)
		// application  (이거는 전역)
		
		//속성지정
		pageContext.setAttribute("name", "나는 pageContext입니다."); 
		request.setAttribute("name", "나는 request입니다.");
		session.setAttribute("name", "나는 session입니다.");
		application.setAttribute("name", "나는 application입니다.");
		
		//받기
		System.out.println("scope_first=======");
		System.out.println("pageContext======="+pageContext.getAttribute("name"));
		System.out.println("request======="+request.getAttribute("name"));
		System.out.println("session======="+session.getAttribute("name"));
		System.out.println("application======="+application.getAttribute("name"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("scope_second_06.jsp");
		dispatcher.forward(request, response); // scope_second.jsp로 넘기기 (서버는 남고 내용만 이동이 될 것이다)
	%>
</body>
</html>