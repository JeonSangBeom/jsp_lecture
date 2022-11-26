<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String avengers[] = request.getParameterValues("avengers");
	request.setAttribute("avengers", avengers); //"avengers" 이것과 아래 ${avebgers}가 같은 것
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 지정한 속성값 이용  -->
	<h1>선택한 어벤져스</h1>
	<ul>
		<c:forEach items="${avengers }" var="a">
			<li>${a }</li>
		</c:forEach>		
	</ul>
	
	<!-- 속성값이 없이 개별적으로 request로 받아와 사용하기  -->
	<h1>선택한 어벤져스</h1>
	<ul>
		<c:forEach items="${paramValues.avengers }" var="a"> <!-- 이렇게 받을 수 있고 이럴 경우는 위처럼 변수를 지정하지 않아도 된다 -->
			<li>${a }</li>
		</c:forEach>
	</ul>
	
	<!-- 마지막 콤마는 안나오게 할때 사용할 수 있는 방법(var을 쓰면 안된다) - varStatus:속성 상태줄떄 사용  -->
	<h1>선택한 어벤져스</h1>
	<c:forEach items="${paramValues.avengers }" var="a" varStatus="status">
		${a } <c:if test="${not status.last }">,</c:if> 
	</c:forEach>
	<div><a href="jstl_checkbox_06.jsp">뒤로</a></div>
</body>
</html>