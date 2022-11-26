<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie cookie = new Cookie("elCookie","elCookie맛있엉용");//(이름, 값) 쿠키 값에 빈칸이 들어가면 오류가 발생할 수 있다
	cookie.setMaxAge(60*3);
	cookie.setPath("/");
	response.addCookie(cookie);	
	// 쿠키는 서버에서 내려간다(그래서 response사용)
	// 쿠키는 처음에는 생성이 안되고 재접속시에만 생성된다 (클라이언트가 들고 오는 것)
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${cookie.elCookie.value } el형식 쿠키 이름 얻어오는 법
</body>
</html>