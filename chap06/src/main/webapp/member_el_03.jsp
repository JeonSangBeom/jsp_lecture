<%@page import="java.util.ArrayList"%>
<%@page import="chap06.MemberDto_03"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- express language라 el로 많이 사용한다 
    	private는 접근이 안되는데 el에서는 접근을 허용한다-->
    
<%
    List<MemberDto_03> memberList = new ArrayList<>(); // 리스트 만들기
     	memberList.add(new MemberDto_03("토르","tor"));
     	memberList.add(new MemberDto_03("토니스타크","ironman"));
     	pageContext.setAttribute("avengers", memberList);// 리스트 담기
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>${avengers[0]}</li> 
		<li>${avengers[0].name} === ${avengers[0].getName()}</li>  
		<li>${avengers[1].name} === ${avengers[1].getName()}</li> 
		<li>${avengers[0].id} === ${avengers[0].getId()}</li> 
		<li>${avengers[1].id} === ${avengers[1].getId()}</li> 
		<!--el은 굳이 get을 붙이지 않아도 된다 / el은 private여도 접근 가능 -->
		
<!-- 	MemberDto [name=토르, id=tor] - to string으로 출력
		토르 === 토르
		토니스타크 === 토니스타크
		tor === 토르
		ironman === 토니스타크  -->
		
	</ul>
</body>
</html>