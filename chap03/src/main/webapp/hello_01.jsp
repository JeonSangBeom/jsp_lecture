<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> <!-- 기호로 자바 파일을 인식 시키는 식 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Jsp</title>
</head>
<body>
	<h1>Hello JSP</h1>
	<h2>
	<% /* <% -> 이 기호가 : 자바에서 사용가능한 변수 -> jsp에서 사용하게 만들기 */
		int num01 = 10;
		int num02 = 20;
		int sum = num01 + num02;
		
		out.println(sum);
		//안에 "<h2>"+sum+"</h2>" 이런식으로 사용도 가능
		//내장 객체 out: (jsp 파일안에 자동으로 들어가 있는 PrintWriter) 
	%>
	</h2>
</body>
</html>