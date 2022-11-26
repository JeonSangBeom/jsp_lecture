<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here01</title>
</head>
<body>
	<% 		
		//쿠키는 resoponse로 내려 보낸다
		Cookie cookie = new Cookie("id","jjang051"); //키 value 형식으로 값을 처리
		cookie.setMaxAge(60*60*24);// 얼만큼 유지 할 건지(초단위)->60*60*24 오늘 하루 / 시간을 설정하지 않으면 session으로 값을 보낸다
		response.addCookie(cookie);//쿠키는 서버에서 로컬로 보내주는 것(문자로)
		//바로 보내는 법
		response.addCookie(new Cookie("age","24"));
		response.addCookie(new Cookie("pw","1234"));
	%>
	<h1>쿠키 보내기...</h1>
	<%
		//쿠키 받을때에는 request로 받는다
		//쿠키는 배열로 읽어야 한다
		Cookie cookies[] = request.getCookies();
		//만든 후 새로고침하기
		if(cookies!=null) {
			for(Cookie c : cookies) { // 문자로 받아온다
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				out.println(cookieName+" : "+cookieValue+"<br>");
			}
			/*
			for(int i = 0;i<cookies.length;i++) {
				String cookieName = cookies[i].getName();
				String cookieValue = cookies[i].getValue();
				out.println(cookieName+" : "+cookieValue+"<br>");
			}
			*/
		}
	%>
</body>
</html>
