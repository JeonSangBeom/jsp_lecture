<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp"  %>
    <main>
      <div class="inner">
	      <h2 class="subTitle">WRITE</h2>
		  <div id="contents">
		  	<!-- 데이터 받아서 처리하기....forward방식으로 데이터 보여주기....  -->
		  	<form method="POST" action="write_process.jsp" id="join" class="form" 
		  	enctype="multipart/form-data">
		  	<!-- 파일 업로드시 중요 enctype="multipart/form-data" - 기본타입 -->
		  		<table>
		  			<colgroup>
		  				<col style="width:20%">
		  				<col style="width:80%">
		  			</colgroup>
		  			<tbody>
		  				<tr>
		  					<th>title<span class="required">*</span></th>
		  					<td>
		  						<input type="text" name="user_title" id="user_title" placeholder="이름를 입력하세요.">
		  					</td>
		  				</tr>
		  				<tr>
		  					<th>link<span class="required">*</span></th>
		  					<td>
		  						<input type="text" name="user_link" id="user_link" placeholder="링크를 입력하세요.">
		  					</td>
		  				</tr>
		  				<tr>
		  					<th>background<span class="required">*</span></th>
		  					<td>
		  						<label><input type="radio" name="user_bg" value="#ff0000"><span>RED</span></label>
		  						<label><input type="radio" name="user_bg" value="#3399cc"><span>ORANGE</span></label>
		  						<label><input type="radio" name="user_bg" value="#0000ff"><span>BLUE</span></label>
		  						<label><input type="radio" name="user_bg" value="#ff00ff"><span>MAGENTA</span></label>
		  						<label><input type="radio" name="user_bg" value="#00ff00"><span>GREEN</span></label>
		  					</td>
		  				</tr>
		  				<tr>
		  					<th>file<span class="required">*</span></th>
		  					<td>
		  						<input type="file" name="user_file" id="user_file" placeholder="파일을 입력하세요.">
		  					</td>
		  				</tr>
		  				<tr>
		  					<th>contents<span class="required">*</span></th>
		  					<td><textarea placeholder="내용을 입력하세요." name="user_contents" id="summernote"></textarea></td>
		  				</tr>
		  			</tbody>
		  		</table>
		  		<div class="btns">
		  			<button type="submit" class="btn btnConfirm">글쓰기</button>
		  			<button type="reset" class="btn btnCancel">취소</button>
		  		</div>
		  	</form>
		  </div>      	
	  </div>
    </main>
    <script>
    	
    	//summernote 적용
    	$("#summernote").summernote({
    		height: 300
    	});
    	
    </script>
<%@ include file="./include/footer.jsp"  %>




