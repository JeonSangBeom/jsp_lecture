<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03</title>
</head>
<body>
	<h1>JSTL if</h1>
	<c:set var="num" value="100"></c:set>
	<%--<c:set var="num" value="100" /> 뒤에 공백이 있을 경우에는 이렇게 사용하여도 무방하다--%>
	<c:set var="str" value="java"></c:set>
	
	
	<h2>if 숫자비교</h2>
	<c:if test="${num%2 == 0 }" var="result">
	<!-- if - tset 안에 조건을 사용한다 -->
		${num }은 짝수 입니다. <!-- 조건이 충족하지 못할 시에는 뜨지 않는다 -->
	</c:if>
	<p>num result : ${result }</p> <!-- 맞을경우 변수로 설정한 값은 true 로 뜬다 -->
	<!-- if 숫자비교
		100은 짝수 입니다.
		num result : true 
		-->
	
	
	<h2>if 문자비교</h2>
	<c:if test="${str eq 'jstl' }" var="result02">
		${str }은 jstl이 맞습니다.
	</c:if>
	<c:if test="${not result02 }" var="result02"> <!-- else 대체 하는 방법 (위에 조건이 맞으면 아래 값을 출력 -->
		${str }은 jstl이 아닙니다.
	</c:if>
	<p>str result : ${result02 }</p>
	<!--if 문자비교
		java은 jstl이 아닙니다.
		str result : true
		-->
	
	
	<h2>주의할 점</h2>
	<c:if test="100" var="result03">
		test에 일반값(숫자나 일반적인 문자 등)이 들어오면 false입니다.
	</c:if>
	<p>result03 : ${result03 }</p>
	<c:if test="true" var="result04">
		test에 true이 들어오면 true입니다(true나 false값은 인정해준다/대소문자 구분없음(TrUe도 가능))
	</c:if>
	<p>result04 : ${result04 }</p>
	<%-- true를 제외한 일반값은 다 false --%>
	<!--주의할 점
		result03 : false 
			
		test에 true이 들어오면 true입니다.
		result04 : true		
		-->	
	
	<c:if test="${true}" var="result05">
		test에 el을 쓴 true이 들어오면 true입니다.
	</c:if>
	<%-- test="  ${true}  " 따옴표 사이에 공백은 허용하지 않는다 false를 리턴한다. / {}안은 상관 없다 --%>
	<p>result05 : ${result05 }</p>
	
	<!--
		test에 el을 쓴 true이 들어오면 true입니다.
		result05 : true  -->
	
</body>
</html>


 