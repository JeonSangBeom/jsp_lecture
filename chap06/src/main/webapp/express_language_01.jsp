<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 표현 언어 알아보기 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01</title>
</head>
<body>
	<!--값 출력하는 방법  -->
	<h1><% out.print("hello"); %></h1>		
	<h1><%= "hello world"  %></h1><!-- 그냥 출력할 값 입력 하면 된다   -->	
	${"hello " } <!-- 달러로 출력하는 방법 : 전부 출력이 가능하다  / html은 못쓴다 '<h1></h1>--> 
	정수 : ${10 }<br>
	실수 : ${4.5 }<br>
	문자열 : ${"jjang051" }<br>
	boolean : ${true}<br>
	null : ${null }<br>
	${10 + 10 }<br>
	${10 / 10 }<br>
	${10 * 10 }<br>
	${10 - 10 }<br>
	${10 > 10 }<br> <!-- false 출력 -->
	${(10 > 15) ? 5 : 3 }<br> <!-- 삼항 연산자도 가능 조건이 맞으면 5가 떨어지고 아니면 3으로 떨어져라 -->
	<!-- 출력 --
	hello 정수 : 10
	실수 : 4.5
	문자열 : jjang051
	boolean : true
	null :
	20
	1.0
	100
	0
	false
	3  -->
</body>
</html>