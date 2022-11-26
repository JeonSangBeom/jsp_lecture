<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="today" value="<%= new Date() %>" />
	<!-- date로 뽑아올경우 그냥 써도 되나 db의 date를 뽑아올 경우 parse로 써야 한다 -->
	<%-- <%= new java.util,Date() %> 이렇게 풀로 써도 되고 임폴트로 가져와도 상관없다 --%>
	<h1>날짜 포맷</h1>
	<ul>
		<li>full : <fmt:formatDate value="${today }" type="date" dateStyle="full" /></li><!-- 2022년 2월 5일 토요일 -->
		<li>short : <fmt:formatDate value="${today }" type="date" dateStyle="short" /></li><!-- 22. 2. 5. -->
		<li>long : <fmt:formatDate value="${today }" type="date" dateStyle="long" /></li><!-- 2022년 2월 5일 -->
		<li>default : <fmt:formatDate value="${today }" type="date" dateStyle="default" /></li><!-- 2022. 2. 5. (아무것도 안쓸경우 이게뜬다) -->
	</ul>
	
	<h1>시간 포맷</h1>
	<ul>
		<li>full : <fmt:formatDate value="${today }" type="time" timeStyle="full" /></li><!-- 오후 1시 7분 50초 대한민국 표준시 -->
		<li>short : <fmt:formatDate value="${today }" type="time" timeStyle="short" /></li><!-- 오후 1:07 -->
		<li>long : <fmt:formatDate value="${today }" type="time" timeStyle="long" /></li><!-- 오후 1시 7분 50초 KST -->
		<li>default : <fmt:formatDate value="${today }" type="time" timeStyle="default" /></li><!-- 오후 1:07:50 (아무것도 안쓸경우 이게뜬다) -->
	</ul>
	
	<h1>날짜 / 시간 포맷 같이쓸떄</h1>
	<ul>
		<li>full : <fmt:formatDate value="${today }" type="both" dateStyle="full" timeStyle="full" /></li>
		<li>short : <fmt:formatDate value="${today }" type="both" dateStyle="short" timeStyle="short" /></li>
		<li>long : <fmt:formatDate value="${today }" type="both" dateStyle="long" timeStyle="long" /></li>
		<li>default : <fmt:formatDate value="${today }" type="both" dateStyle="default" timeStyle="default" /></li>
	</ul>
<!-- 	full : 2022년 9월 5일 월요일 오후 5시 35분 51초 대한민국 표준시
		short : 22. 9. 5. 오후 5:35
		long : 2022년 9월 5일 오후 5시 35분 51초 KST
		default : 2022. 9. 5. 오후 5:35:51 -->
	
	
	<h1>날짜 / 시간 패턴</h1>
	<!-- 가장 많이 사용하는 방법이다 -->
	<ul>
		<li>pattern : <fmt:formatDate value="${today }" type="both" pattern="yyyy-MM-dd hh:mm:ss" /></li>
		<!-- MM과 mm은 시간과 날짜에 대한 구분을 위해  대문자와 소문자 잘 파악하기 yyyy년MM월dd일 - 이것도 출력 잘 된다 
			패턴으로 나뉘기 때문에 타입과는 무관하다-->
	</ul>
</body>
</html>