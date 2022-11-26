<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  <!-- 포맷 설정 -->

<!-- 자바스크립트와 차이
javascript는 클라이언트가 바꾸는 것
format은 랜더링이 먼저 된 것(세팅된 것) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>format 설정</h1>
	
	<c:set var="num01" value="12345" />			
	<ul>
		<li>콤마 : <fmt:formatNumber value="${num01 }" /></li>
		<li>콤마 : <fmt:formatNumber value="${num01 }" groupingUsed="false"/></li><!-- groupingUsed="false" = 콤마 찍지않고 출력해주는 것 -->
	</ul>
	<!-- 콤마 : 12,345 fmt:formatNumber - 세자리마다 넘버 찍히게 세팅
		 콤마 : 12345  groupingUsed="false -콤마가 안찍히게 만들때-->
	
	
	<!-- 세팅 먼저 -->		
	<fmt:formatNumber value="${num01 }" type="currency" var="currencyNum01" /> <!-- type="currency" = 원 기호 -->	
	<h1>통화기호</h1>
	<ul>
		<li>${currencyNum01 } <!-- fmt에서 지정한 변수 --></li>
	</ul>
	<!-- ₩12,345 type="currency" - 세팅한 타입-->
	
		
	<h1>퍼센트</h1>
	<fmt:formatNumber value="0.03" type="percent" var="percentNum02" /><!-- type="percent" = 퍼센트 -->
	<ul>
		<li>${percentNum02 }</li> <!-- 답 3% 출력 -->
	</ul>
	<!-- 3% -->
	
	<h1>문자를 숫자로</h1>
	<c:set value="6,789.01" var="num02" /> <!-- 문자로 들어가 있는 것 - ,도 들어간 상태-->
	<fmt:parseNumber value="${num02 }"  var="parserNum02" /> <!-- fmt:parseNumber 문자를 숫자로 출력 - 패턴이 적용되지 않는다 -->
	<fmt:parseNumber value="${num02 }" integerOnly="true" var="parserNum03" /> <!-- integerOnly="true" = 정수 출력 -->
	<ul>
		<li>${parserNum02 }</li> 
		<li>${parserNum03 }</li> 
		<li><fmt:formatNumber value="${parserNum02 }" pattern="00,000.00" /></li> <!-- 다시 formatNumber로 바꿔 적용 가능 -->
	</ul>
	<!--6789.01
		6789
		06,789.01  -->
</body>
</html>