<%@page import="com.jjang051.common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- taglib - 태그 라이브러리를 쓰겠다는 뜻 
	 접두어로 preix = "c"사용하겠다
	 jstl을 라이브러리로 사용하는 태그 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01</title>
</head>
<body>
	<!-- view (f12 - 화면(처리되는 곳) 검사시 노출되지 않는다)-->
	<!-- c(설정한 접두어):set - 변수 설정 하는 것 
		 var = 변수명-->
	<c:set var="directVar" value="100"></c:set>
	<c:set var="elVar" value="${directVal mod 5 }"></c:set><!--el문법도 사용 가능 mod(나머지)  -->
	<c:set var="betweenVar">변수값 설정</c:set> <!-- 이렇게도 쓸 수 있다. 많이 사용하지는 않는다. value에 값을 지정하여 사용하는 것이 더 좋다 -->
	<c:set var="personVar01" value='<%= new Person ("홍길동",29) %>' scope="request" ></c:set> <!-- scope="request" - 어디에 저장할 것인지 -->
	<ul>	
		<!-- 출력할땐 el문법으로 -->
		<li>directVar : ${directVar }</li>
		<li>elVar : ${elVar }</li>
		<li>betweenVar : ${betweenVar }</li>
		<li>이름 : ${personVar01.name }</li>
		<li>나이 : ${personVar01.age }</li>
		<!-- 출력 --
		directVar : 100
		elVar : 0
		betweenVar : 변수값 설정
		이름 : 홍길동
		나이 : 29 -->
	</ul>
</body>
</html>