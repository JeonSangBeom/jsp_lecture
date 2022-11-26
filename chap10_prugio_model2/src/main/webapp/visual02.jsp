<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/header02.jsp"%>
<main class="formBox">
	<div class="container">
		<div id="contents">
			<form method="POST" action="VisualUpload02.do" id="join" class="form"  enctype="multipart/form-data">
		  		<table>
		  			<colgroup>
		  				<col style="width:20%">
		  				<col style="width:80%">
		  			</colgroup>
		  			<tbody>
		  				<tr>
		  					<th>slogan <span class="required">*</span></th>
		  					<td>
		  						<input type="text" name="user_slogan1" id="user_slogan1" placeholder="슬로건을 입력하세요.">
		  					</td>
		  				</tr>
		  				<tr>
		  					<th>visual <span class="required">*</span></th>
		  					<td>
		  						<input type="file" name="user_visual1" id="user_visual1" placeholder="배경으로 쓸 이미지를 입력하세요.">
		  					</td>
		  				</tr>
		  				
		  			</tbody>
		  		</table>
		  		<div class="btns end">
		  			<button class="btn btnSmall" id="btnPlus">+</button>
		  			<button class="btn btnSmall" id="btnMinus">-</button>
		  		</div>
		  		<div class="btns">
		  			<button type="submit" class="btn btnConfirm">확인</button>
		  			<button type="reset" class="btn btnCancel">취소</button>
		  		</div>
		  	</form>
		</div>
	</div>
</main>
<script>
// 내용 추가 할때 스크립 이용
// script안에 서el문법을 사용하면 오류가 발생한다(''안에서 사용하는 문법과 동일하기 때문)
// 이럴땐 다른 폴더안 파일 js 로 이동을 시키거나 \를 붙여서 사용하면 더 이상 el문법이 아니게 된다
	let count = 1;
	$("#btnPlus").on("click",function() {
		count++;// name값이 동일하면 하나의 값만 날아가기 때문에 count사용해 주어야 한다
		$("#join tbody").append(`
			<tr>
				<th>slogan <span class="required">*</span></th>
				<td>
					<input type="text" name="user_slogan\${count}" id="user_slogan\${count}" placeholder="슬로건을 입력하세요.">
				</td>
			</tr>
			<tr>
				<th>visual <span class="required">*</span></th>
				<td>
					<input type="file" name="user_visual\${count}" id="user_visual\${count}" placeholder="배경으로 쓸 이미지를 입력하세요.">
				</td>
			</tr>		
		`);
		return false; // 디폴트 값이 submit이기 때문에 날아가는 것을 막기 위해(VisualUpload02.do로) false로 사용
	});
	$("#btnMinus").on("click",function(){
		if(count>1){//제일 처음은 제거가 되면 안되기 때문에 1이하는 제거가 안되게
			count--;
			$("#join tbody tr:last-child").remove();
			$("#join tbody tr:last-child").remove();
			//tr을 제거하여 없애주기(두개씩 추가기 때문에 두개씩)
		}
		return false;
	});
</script>
	
<%@ include file="./include/footer.jsp"%>

