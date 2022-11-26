<%@page import="java.util.ArrayList"%>
<%@page import="com.jjang051.model.MemberDao"%>
<%@page import="com.jjang051.model.MemberDto"%>
<%@page import="com.jjang051.jdbc.JDBCConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- jstl 사용시 라이브러리 태그를 미리 가져와야 한다 / lib 폴더 안 jstl.jar파일도 있어야 한다 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 포맷을 할때 사용 -->
<%@ include file="./include/header.jsp" %>
<%
	MemberDao memberDao = new MemberDao();
	ArrayList<MemberDto> memberList = memberDao.showAllMember(); // 전부 뿌려준다 / 리턴 (arraylist출력) 
	request.setAttribute("memberList", memberList);// 여기서 arraylist를 받는다
	System.out.print("멤버리스트 0"+memberList.get(0));
%>
<main>
	<div class="container">
		<h2 class="subTitle">MEMBER LIST</h2>
		<div id="contents">
			<div class="tableBox">
				<table>
					<colgroup>
						<col style="width: 30px">
						<col style="width: 100px">
						<col style="width: 100px">
						<col style="width: 200px">
						<col style="width: 150px">
						<col style="width: 100px">
						<col>
						<col style="width: 200px">
					</colgroup>
					<thead>

						<tr>
							<th>NO</th>
							<th>ID</th>
							<th>NAME</th>
							<th>EMAIL</th>
							<th>PHONE</th>
							<th>우편번호</th>
							<th>주소</th>
							<th>DATE</th>
						</tr>

					</thead>
					<tbody>
						<c:forEach items="${memberList}" var="memberDto" varStatus="loop" begin="1">
							<fmt:parseDate value="${memberDto.regDate }" var="convertDate" pattern="yyyy-MM-dd HH:mm:ss" /> 
							<!--  fmt:parseDate로 문자를 날짜로 바꿔 값을 받아와서 formatDate로 원하는 패턴을 설정하여 데이터 뿌리기  -->
							<tr>
								<!-- <td>${memberDto.no }</td> - 이거 사용시 순서가 엉망으로 나온다 -->
								<td>${loop.index }</td> <!-- no가 순서대로 나오게 만들려면 이 방법으로 쓰면 된다 begin설정 안할시 0 부터 출력-->
								<td><a href="./member_info.jsp?user_id=${memberDto.id }">${memberDto.id }</a></td>
								<td>${memberDto.name }</td>
								<td>${memberDto.email }</td>
								<td>${memberDto.phone }</td>
								<td>${memberDto.zipCode }</td>
								<td>${memberDto.address }</td>
								<%-- <td>${memberDto.regDate }</td> --%>
								<td><fmt:formatDate value="${convertDate }" pattern="yyyy-MM-dd HH-mm-ss" /></td>
								<!-- type를 줘서(time,date,both) 출력도 가능 -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>

<%@ include file="./include/footer.jsp"%>