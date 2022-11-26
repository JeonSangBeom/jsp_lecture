<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num01 = 3;
	pageContext.setAttribute("num02", 4);
	pageContext.setAttribute("num03", "5");	
	pageContext.setAttribute("num04", "6");
	
	pageContext.setAttribute("nullString", null);
	pageContext.setAttribute("emptyString", "");
	
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>el operator</h1>
	<ul>
		<li>${num01 = 7 }</li> <!-- 그냥 num01 입력시 안나온다 -->
		<li>${num05 = 10;""} ==> ${num05}</li><!-- 뒤에 ;"" 붙일시 안나온다 -->
		<li>${num02 }</li>
		<li>${num02 + num03 }</li>
		<li>${num02 / num03 } ==> ${num02 div num03 }</li>
		<li>${num02 % num03 } ==> ${num02 mod num03 }</li>
		<li>${num02 > num03 } ==> ${num02 gt num03 }</li>
		<li>${num02 < num03 } ==> ${num02 lt num03 }</li>
		<li>${num02 >= num03 } ==> ${num02 ge num03 }</li>
		<li>${num02 <= num03 } ==> ${num02 le num03 }</li>
		<li>${num02 == num03 } ==> ${num02 eq num03 }</li>
		<li>${num03 }</li>
		<li>${num04 }</li>
		<li>${empty nullString }</li>
		<li>${empty emptyString }</li>
		<li>${nullString + 10 }</li>
		<li>${nullString > 10 }</li>
		<!-- 
		출력 --
		7
		==> 10
		4
		9
		0.8 ==> 0.8
		4 ==> 4
		false ==> false
		true ==> true
		false ==> false
		true ==> true
		false ==> false
		5
		6
		true - 공백일 경우 true로 표시 된다
		true
		10
		false 
		 -->
		
	</ul>
</body>
</html>