<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% // expression - 이렇게 바로 불러서 사용 가능
	pageContext.setAttribute("scope", "page");
	request.setAttribute("scope", "request");
	session.setAttribute("scope", "session");
	application.setAttribute("scope", "application");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02</title>
</head>
<body>
	<ul>
		<!-- {scope } 이렇게 하나만 쓸경우 유효범위가 작은 곳 부터 보여진다 pageContext부터-->
		 
		<li>${pageScope.scope }</li> 
		<li>${requestScope.scope }</li> <!-- 보통 변수명을 같게 안두어서 이런 경우는 거의 없다(현재 변수 명이 같아 값과 이름을 같이 사용)-->
		<li>${sessionScope.scope }</li>
		<li>${applicationScope.scope }</li>
	</ul>
</body>
</html>