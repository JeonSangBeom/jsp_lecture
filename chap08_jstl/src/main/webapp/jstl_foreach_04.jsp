<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.jjang051.common.Person"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>for each</h1>
	<pre>
		for(int i=0;i<100;i++){
			test
		} <!-- 자바 문법 -->
	</pre>
	<c:forEach var="i" begin="1" end="10" step="1"> <!-- begin : 시작 값 end : 끝 step : 증가 폭(안써도 1씩 증가한다 / 보통 2 이상) -->
		<p>${i }번째 입니다.</p> <!-- 반복해서 10까지 값이 출력된다 -->
	</c:forEach>
	
	<h2>varStatus의 값 출력해보기...</h2>
	<table border="1">
		<c:forEach begin="3" end="5" var="i" varStatus="loop"> <!-- varStatus - 변수의 상태를 보는 것(반복문에서의 변수) -->
			<tr>
				<td>count : ${loop.count }</td> <!-- 처음부터 갯수를 셀때(순서대로) -->
				<td>index : ${loop.index }</td> <!-- 시작지점부터 갯수를 반복 -->
				<td>current : ${loop.current }</td> 
				<td>first : ${loop.first }</td> <!-- 처음인지 알고 싶을떄 -->
				<td>last : ${loop.last }</td> <!-- 끝인지 알고 싶을 때 -->
			</tr>
		</c:forEach>
	</table>
	
	
	<h2>for 02</h2>
	<%
		String fruits[]= {"banana","apple","kiwi","peach"};
		//el${}은 속성값만 쓸 수 있다. - 바로 사용할 경우 
		
		String colors[] = {"red","green","blue","black","magenta"};
		request.setAttribute("colors", colors);
		
		List<Person> lists = new ArrayList<>();
		lists.add(new Person("아이언맨",45));
		lists.add(new Person("헐크",50));
		lists.add(new Person("토르",1900));
		
		Map<String,Person> maps = new HashMap<>();
		maps.put("ironman",new Person("아이언맨",45));
		maps.put("hulk",new Person("헐크",50));
		maps.put("tor",new Person("토르",1900));
		//속성에 담거나(필드값), c:set으로 값을 지정하면 el${}를 쓸 수 있다.
		// 아니면 <%를 써야한다
	%>
	<c:set var="avengers" value="<%=lists %>" />
	<c:set var="avengersMap" value="<%=maps %>" />
	
	
	<!-- 배열을 출력할때 사용(items) : 퍼센트지(표현식)로 사용해야 한다-->
	<ul>
	<c:forEach items="<%= fruits %>" var="f"> 
		<li>${f }</li>
	</c:forEach>
	</ul>
	
	
	<!-- 위에 따로 속성값에 담았기 때문에 바로 사용할 수 있다 -->
	<ul>
	<c:forEach items="${colors }" var="c" begin="1" end="3"> 
		<li style="color:${c}; font-size:20px">${c }</li>
	</c:forEach>
	</ul>
	
	
	<!-- 위에 속성값으로 avengers에 list 값을 넣어두었다- c:set 사용 -->
	<ul>
		<c:forEach items="${avengers }" var="a"> 
			<li>이름 : ${a.name } / 나이 : ${a.age }</li>
		</c:forEach>
	</ul>
	
	
	<!-- map에 담았을떄 사용법 -->
	<ul>
		<c:forEach items="${avengersMap }" var="m">
			<li>
				key ==> ${m.key } <br>
				value ==> 이름 : ${m.value.name } / 나이 : ${m.value.age }
			</li>
		</c:forEach>
	</ul>
</body>
</html>



