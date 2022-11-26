<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/header02.jsp"%>
<main class="formBox">
	<div class="container">
		<div id="contents">
			<form method="POST" action="VisualUpload.do" id="join" class="form"  enctype="multipart/form-data">
		  		<table>
		  			<colgroup>
		  				<col style="width:20%">
		  				<col style="width:80%">
		  			</colgroup>
		  			<tbody>
		  				<tr>
		  					<th>slogan <span class="required">*</span></th>
		  					<td>
		  						<input type="text" name="user_slogan01" id="user_slogan01" placeholder="슬로건을 입력하세요.">
		  					</td>
		  				</tr>
		  				<tr>
		  					<th>visual <span class="required">*</span></th>
		  					<td>
		  						<input type="file" name="user_visual01" id="user_visual01" placeholder="배경으로 쓸 이미지를 입력하세요.">
		  					</td>
		  				</tr>
		  			</tbody>
		  		</table>
		  		<div class="btns">
		  			<button type="submit" class="btn btnConfirm">확인</button>
		  			<button type="reset" class="btn btnCancel">취소</button>
		  		</div>
		  	</form>
		</div>
	</div>
</main>

<%@ include file="./include/footer.jsp"%>