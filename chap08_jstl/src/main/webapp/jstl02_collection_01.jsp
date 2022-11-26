<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.jjang051.common.Person"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02</title>
</head>
<body>
	<h1>Collection에 값 담아보기...</h1>
	<h2>ArrayList(jstl 배열 사용하기)</h2>
	<%
		ArrayList<Person> personList = new ArrayList<>();
	 	//연산자(<>)를 통해 Person을 담겠다
		personList.add(new Person("아이언맨",45));
		personList.add(new Person("헐크",50));
		personList.add(new Person("토르",1900));
	%>
	<c:set var = "jstlPersonList" value='<%=personList %>' scope="request"></c:set> 
	<!-- scope="request" 쓰지않으면 pagecontext로 저장이 된다
		 request를 쓰는 이유가 forward를 통해 다른 페이지로 넘길 수 있기 때문이다 -->
	<ul>
		<!-- jstl 배열 사용하기 -->
		<li>이름 : ${jstlPersonList[1].name }</li>
		<li>나이 : ${jstlPersonList[1].age }</li>
	</ul>
	
	<h2>Map</h2>
	<%
		Map<String,Person> personMap = new HashMap<>();
		//Map을 쓴게 hashmap을 구현한 것과 같은 것이다 Map은(참조를 해준다)
		personMap.put("ironman", new Person("토니스타크",45));
		personMap.put("hulk", new Person("배너박사",50));
	%>
	<c:set var="jstlPersonMap" value='<%= personMap %>' scope="request"></c:set>
	<ul>
		<li>이름 : ${jstlPersonMap.ironman.name }</li>
		<li>나이 : ${jstlPersonMap.ironman.age }</li>
	</ul>
</body>
</html>

