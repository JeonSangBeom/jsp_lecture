<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>변수 및 함수 선언하기 다른 방법</title>
</head>
<body>
	<%!
		String msg = "안녕하세요.";
		int num01 = 10;
		int num02 = 20;
		public int abs(int num) {
			if(num<0) {
				return num = -num;
			} else  {
				return num;
			}
		}
	%>
	<!-- html 주석 -->
	<%-- jsp 주석 --%>
	<p><%= msg %></p>
	<p><%= num01 %></p>
	<p><%= num02 %></p>
	<p>-5의 절대값은 <%= abs(-5) %></p>
	<!-- 안에 쓰는 것을 표현식이라 한다(일일이 html 같은 것을 쓰지 않고 이런 식으로 쓸 수 있다) -->
</body>
</html>