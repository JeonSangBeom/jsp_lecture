<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="./include/header.jsp" %>
<main>
	<div class="inner">
		<h2 class="subTitle">BOARD LIST</h2>
		<div id="contents">
			<div class="tableBox">
				<table>
					<colgroup>
						<col style="width: 30px">
						<col style="width: 100px">
						<col>
						<col style="width: 200px">
						<col style="width: 200px">
						<col style="width: 50px">
					</colgroup>
					<thead>
						<tr>
							<th>NO</th>
							<th>NAME</th>
							<th>SUBJECT</th>
							<th>EMAIL</th>
							<th>DATE</th>
							<th>HIT</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${boardList }" var="boardDto" varStatus="loop">
						<tr>
							<td>${boardDto.no }</td>
							<td>${boardDto.name }</td>
							<td class="space${boardDto.reStep }">
								<c:if test="${boardDto.reStep > 1 }">
									<span class="material-icons">subdirectory_arrow_right</span>
								</c:if>
								<a href="View.do?no=${boardDto.no }">${boardDto.subject }</a>
							</td>
							<td>${boardDto.email }</td>
							<td>${boardDto.regDate }</td>
							<td>${boardDto.hit }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<form action="BoardSearchList.do" class="form" method="GET">
					<div class="searchBox">
						<select name="search_select">
							<option value="subject"  ${param.search_select=="subject"?"selected":""}>제목</option>
							<option value="name"   ${param.search_select=="name"?"selected":""}>작성자</option>
							<option value="contents" ${param.search_select=="contents"?"selected":""}>내용</option>
							<!-- 삼항연산자를 이용하여 만들기 - 넘어온 select와 같으면(=?) selected를 해주고 아니면 공백  -->
						</select>
						<input type="text" placeholer="검색할 내용을 입력해 주세요." name="search_word" value="${param.search_word }">
						<button class="btn btnBlack">검색</button>
					</div>
				</form>
					<!-- 서취 셀렉트를 받았는데 서브젝트이면 selected 아니면 공백 -->
				<div class="btns">
					<a href="Write.do" class="btn btnConfirm">글쓰기</a>
				</div>
			</div>
		</div>
	</div>
</main>

<%@ include file="./include/footer.jsp"%>