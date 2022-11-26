<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Map<String, String> map = new HashMap<>(); //Map<String(제네릭(일반적인)), String>
	map.put("한글","훈민정음"); // 맵은 put으로 세팅(키, 값)
	map.put("eng","alphabet");
	pageContext.setAttribute("language", map);
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>영문 key : ${language.eng }===${language["eng"] }</p> <!-- 둘다 같은 값이 나온다  'alphabet'-->
	<p>한글 key : ${language["한글"] }</p>
	<!-- 한글은 그냥은 못쓰고 [""]안에 쓸 수 있다 -->
</body>
</html>