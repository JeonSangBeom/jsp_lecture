<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>url 태그로 링크걸어 값을 보내기</h1>
	<c:url value="include/other.jsp" var="link">
		<c:param name="user_param01" value="doctor stranger"></c:param>
		<c:param name="user_param02">호크아이</c:param> <!-- value의 값에 쓰는게 정상이지만 이렇게 써도 사용은 가능하다 -->
	</c:url>
	<a href="${link }">other.jsp로 링크 걸기</a>
</body>
</html>