<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% //세션 값 읽기
	String id = (String) session.getAttribute("id");// 리턴 타입이 오브젝트(형변환 필요)
	String pw = (String) session.getAttribute("pw");
	int age = (int) session.getAttribute("age");//integer을 써도 상관 없다
	//int age = (Integer)(session.getAttribute("age")); / 이것도 가능
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02</title>
</head>
<body>
	<ul>
		<li>id : <%= id %></li><!-- 진짜 아이디(html) : 변수로 지정된 위의 아이디 -->
		<li>pw : <%= pw %></li>
		<li>age : <%= age %></li>
	</ul>
	<h2>세션의 여러가지 정보들...</h2>
	<ul>
		<li>세션 유지 시간 : <%= session.getMaxInactiveInterval() %></li> <!--기존에 설정된 값(내장) 1800(30분) -->
		<li>세션 ID : <%= session.getId() %></li>
		<li>최초 요청 시간  : <%= session.getCreationTime() %></li> <!-- 이상한 숫자가 뜨는데 이것을 데이터 포맷으로 바꿔줘야 볼 수 있다 -->
		<!-- 출력되는 것들 --
			세션 유지 시간 : 1800
			세션 ID : 3188F21766B471785D7E5E173899D406
			최초 요청 시간 : 1661401651807  -->
	</ul>
</body>
</html>