<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>
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
						<c:forEach items="${boardList }" var="boardDto" varStatus="loop" begin="0" end="${totalPage }">
						<tr>
							<%-- <td>${boardDto.no }</td> --%>
							<td>${totalPage - (clickPage-1)*listPerPage - loop.index}</td>
							<td>${boardDto.name }</td>
							<td class="space${boardDto.reStep } left">
								<c:if test="${boardDto.reStep > 1 }">
									<span class="material-icons">subdirectory_arrow_right</span>
								</c:if>
								<a href="View.do?no=${boardDto.no }&num=${boardDto.num}">${boardDto.subject }</a>
							</td>
							<td>${boardDto.email }</td>
							<td>${boardDto.regDate }</td>
							<td>${boardDto.hit }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination">
					<ul>
						 
						<!-- <li><a href="BoardList.do?start=1&end=5">1</a></li>
						<li><a href="BoardList.do?start=6&end=10">2</a></li>
						<li><a href="BoardList.do?start=11&end=15">3</a></li>
						<li><a href="BoardList.do?start=16&end=20">4</a></li> -->
						<c:if test="${startPage!=1 }">
						<li><a href="BoardList.do?clickPage=${startPage-pageBlock }"><span class="material-icons">chevron_left</span></a></li>
						</c:if>
						<c:forEach var="page" begin="${startPage }" end="${endPage }" step="1" varStatus="loop">
							<li class="${clickPage==page ? 'active':'' }"><a href="BoardList.do?clickPage=${page }">${page }</a></li>
						</c:forEach>
						<c:if test="${endPage!=lastPage }">
						<li><a href="BoardList.do?clickPage=${startPage+pageBlock }"><span class="material-icons">chevron_right</span></a></li>
						</c:if>
					</ul>
				</div>
				<form action="BoardSearchList.do" class="form" method="GET">
					<div class="searchBox">
						<select name="search_select">
							<option value="subject">제목</option>
							<option value="name" selected>작성자</option>
							<option value="contents">내용</option>
						</select>
						<input type="text" placeholer="검색할 내용을 입력해 주세요." name="search_word">
						<button class="btn btnBlack">검색</button>
					</div>
				</form>
				<div class="btns">
					<!-- 로그인 됐을때에만 글씨기가 보이게 설정하는 법 c:if -->
					<%-- <c:if test="${not empty loggedMember }"> --%>
						<a href="Write.do" class="btn btnConfirm">글쓰기</a>
					<%-- </c:if> --%>
				</div>
			</div>
		</div>
	</div>
</main>
<script>
	console.log(${not empty loggedMember });
	$(".btnConfirm").on("click",function(){
		if(${empty loggedMember }) {
			alert("로그인 해야 글을 쓸 수 있습니다.");
			location.href="../member/Login.do";
			return false;
		} 
	});
</script>
<%@ include file="../include/footer.jsp"%>

