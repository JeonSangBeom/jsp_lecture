<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp"  %>
    <main>
      <div class="container">
	      <h2 class="subTitle">JOIN</h2>
		  <div id="contents">
		  	<!-- 데이터 받아서 처리하기....forward방식으로 데이터 보여주기....  -->
		  	<form method="POST" action="delete_process.jsp" id="join" class="form">
		  		<input type="hidden" name="user_id" value="${param.user_id }">
		  		<!-- 쿼리 파라미터로 넘어오는 값들을 위와 같이 받아 사용할 수 있다 -->
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
