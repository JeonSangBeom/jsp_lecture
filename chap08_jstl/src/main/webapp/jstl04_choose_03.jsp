<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>swich 와 비슷</title>
</head>
<body>
	<h1>jstl choose when otherwise (기본 문법)</h1>
	<c:set var="num" value="101" />
	<!-- 이곳에 조건을 단다 -->
	<c:choose> 
		<c:when test="${num % 2 ==0 }">
			${num }은 짝수 입니다.
		</c:when>
		<c:otherwise> <!-- else 역할 -->
			${num }은 홀수 입니다.
		</c:otherwise>
	</c:choose>
	<!-- 출력
	 	101은 홀수 입니다  -->
	
	
	<h2>국 영 수 평균 구해보기</h2>
	<form>
		<div>국어 : <input type="text" name="kor">   </div>
		<div>영어 : <input type="text" name="eng">   </div>
		<div>수학 : <input type="text" name="math">   </div>
		<button>전송</button>
	</form>
	<c:if test="${not (empty param.kor or empty param.eng or empty param.math) }" var="result">
	<!-- param.kor 또는 eng, math 안이 비어 있지 않으면(empty - 공백이 있는지 알아보는 것)/ 비어있는게 아니라면 다음 단계로 넘어가서 --> 
		<c:set var="avg" value="${(param.kor+param.eng+param.math)/3 }" />
		<p>평균은 ${avg }</p>
		<c:choose>
			<c:when test="${avg>=90 }">A입니다.</c:when>
			<c:when test="${avg>=80 }">B입니다.</c:when>
			<c:when test="${avg>=70 }">C입니다.</c:when>
			<c:when test="${avg>=60 }">D입니다.</c:when>
			<c:otherwise>F입니다.</c:otherwise>
		</c:choose>
	</c:if>
	<p>${result }</p> <!-- 조건 밖에 써야 출력 가능 -->
</body>
</html>

