<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>변수 및 함수 선언하기</title>
</head>
<body>
	<%! //메서드를 사용할때는 기호 뒤에 !를 붙여 선언을 해두어야 사용할 수 있다
		String msg = "안녕하세요.";
		int num01 = 10;
		int num02 = 20; 
		// 변수 선언하기
		public int abs(int num) { //절대값 구하는 식(메서드)
			if(num<0) {
				return num = -num;
			} else  {
				return num;
			}
		}
	%>
	<%
		out.println("<p>"+msg+"</p>");
		out.println(num01+"<br>");
		out.println(num02+"<br>");
		out.println("-5의 절대값은 "+abs(-5)+"<br>");
	%>
</body>
</html>