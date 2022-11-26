<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp"  %>
<%
	//값 받아오기
	String user_id =  (String) session.getAttribute("id");
	System.out.print(user_id);
%>
    <main>
      <div class="container">
	      <h2 class="subTitle">Delect</h2>
		  <div id="contents">
		  <!-- 회원탈퇴를 누르면 delete_process.jsp 로 이동 -->
		  	<form method="POST" action="delete_process.jsp" id="join" class="form">		  	
		  		<input type="hidden" name="user_id" value="<%=user_id%>">
		  		<!-- delect까지는 user_id값이 넘어 오고 있고 process에 값을 넘기기 위해서 임시로 input을 생성(숨겨서) -->
		  		<table style="width:500px; margin:auto">
		  			<tbody>
		  				<tr>
		  					<th>PASSWORD</th>
		  					<td><input type="password" name="user_pw" placeholder="password"></td>		
		  				</tr>
		  			</tbody>
		  		</table>
		  		<div class="btns">
		  			<button type="submit" class="btn btnConfirm">회원탈퇴</button>
		  		</div>
		  	</form>
		  </div>      	
	  </div>
    </main>
    <script>
    
<%@ include file="./include/footer.jsp"  %>