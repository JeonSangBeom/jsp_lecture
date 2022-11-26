<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage = "error.jsp" %>
<!-- 에러 페이지 표시 방법 -> jsp파일로 error을 하나 더 생성 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>0으로 나누면 오류가 뜹니다.</p>
	<%= 2/0  %> <!-- 자바 코드 쓰기 -->
</body>
</html>