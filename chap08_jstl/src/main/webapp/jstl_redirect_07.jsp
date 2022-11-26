<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 시작하자마자어디로 옮기는 것 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- request는 forward로만 전달이 된다 -->	
	<c:redirect url="include/other.jsp">
	<!-- redirect는 시작하자마자 주소를 넘길 수 있다(변수 포함) / 쿼리 스트링과 같다 -->
		<c:param name="user_param01" value="토르"  />
		<c:param name="user_param02" value="헐크"  />
		<!-- c:param 변수를 넘기는 것 -->
	</c:redirect>
</body>
</html>
