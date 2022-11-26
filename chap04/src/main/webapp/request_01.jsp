<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request info</title>
</head>
<body>
	<!-- 내장 객체 () import 안해도 됨 - jsp안에 자동으로 들어가 있는 객체들
		 out(내보내는 것), 
		 request(받는 곳/요청을 보내는 곳에서만 변수 확인 가능),
		 response(응답),
		 session(서버에 저장되는 쿠키 / 브라우저,서버(다음,네이버 등등) 별로는 독립적),
		 
		 --아래 두개는 잘 쓰지 않는다
		 pageContext(페이지(현재 jsp) 내에서만 유효한 것),
		 application(getContextPath(실행환경 chap04) 안에서 가져올 것이 있을때 사용)
	-->
	<ul>
		<!-- request - 여러가지 출력 방식을 알아 볼 수 있다 -->
		<li>컨텍스트 패스 : <%= request.getContextPath()  %></li>  <!-- 실행 환경 / 프로젝트를 뽑아 올 수 있다 -->
		<li>요청방식 : <%= request.getMethod()  %></li>
		<li>요청 URL : <%= request.getRequestURL()  %></li>
		<li>요청 URI : <%= request.getRequestURI()  %></li>
		<li>서버 이름 : <%= request.getServerName()  %></li>
		<li>서버 프로토콜 : <%= request.getProtocol()  %></li>
	</ul>
	<!--  출력
	컨텍스트 패스 : /chap04
	요청방식 : GET
	요청 URL : http://localhost:8090/chap04/request.jsp
	요청 URI : /chap04/request.jsp
	서버 이름 : localhost
	서버 프로토콜 : HTTP/1.1
	  -->
</body>
</html>