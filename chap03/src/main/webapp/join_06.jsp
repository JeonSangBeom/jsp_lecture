<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp"  %>
    <main>
      <div class="container">
	      <h2 class="subTitle">JOIN</h2>
		  <div id="contents">
		  	<!-- 데이터 받아서 처리하기....forward방식으로 데이터 보여주기....  -->
		  	<form method="POST" action="join_process.jsp" id="join" class="form">
		  		<table>
		  			<colgroup>
		  				<col style="width:20%">
		  				<col style="width:80%">
		  			</colgroup>
		  			<tbody>
		  				<tr>
		  					<th>아이디 <span class="required">*</span></th>
		  					<td><input type="text" name="user_id" id="user_id" placeholder="아이디를 입력하세요."></td>
		  				</tr>
		  				<tr>
		  					<th>패스워드 <span class="required">*</span></th>
		  					<td><input type="password" name="user_pw" id="user_pw" placeholder="비밀번호를 입력하세요."></td>
		  				</tr>
		  				<tr>
		  					<th>패스워드 확인 <span class="required">*</span></th>
		  					<td><input type="password" name="user_pw_confirm" id="user_pw_confirm" placeholder="비밀번호를 입력하세요."></td>
		  				</tr>
		  				<tr>
		  					<th>이름 <span class="required">*</span></th>
		  					<td><input type="text" name="user_name" id="user_name" placeholder="이름을 입력하세요."></td>
		  				</tr>
		  				<tr>
		  					<th>주소 <span class="required">*</span></th>
		  					<td>
		  						<div>
		  							<!--id - script에서 사용할 주소 및 jquery에 필요  -->
		  							<input type="number" name="zipcode" placeholder="우편번호" class="short" id="zipcode">
		  							<button class="btn btnZipcode">우편번호</button>
		  						</div>
		  						<div class="addrBox">
		  							<input type="text" name="address01" placeholder="기본주소" id="address01">
		  							<span class="sticker">기본 주소</span>
		  						</div>
		  						<div class="addrBox">
		  							<input type="text" name="address02" placeholder="나머지 주소" id="address02">
		  							<span class="sticker">나머지 주소</span>
		  						</div>
		  					</td>
		  				</tr>
		  			</tbody>
		  		</table>
		  		<div class="btns">
		  			<button type="submit" class="btn btnConfirm">회원가입</button>
		  			<button type="reset" class="btn btnCancel">취소</button>
		  		</div>
		  	</form>
		  </div>      	
	  </div>
    </main>
    <script>
    // 폼태그 안에 들어간 버튼은 디폴트가 submit(정보를 보내는 것)이다 -> 리턴 값을 false로 주어야 데이터가 안날라간다
    $(".btnZipcode").on("click",function(){ // btnZipcode 를 클릭 했을때 
    	postCode(); // 호출
    	return false;
    });
    
    $(".btnConfirm").on("click",function(){// 회원가입 클릭시
    	if($("#user_id").val()==="") {// #user_id 의 .val가 공백이면 
    		alert("아이디를 입력하세요."); // 경고창 발생
    		$("#user_id").focus();// 포커스는 id창에
    		return false;
    	} else {
    		
    	}
    });
    
    function postCode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                let roadAddr = data.roadAddress; // 도로명 주소 변수
                let extraRoadAddr = ''; // 참고 항목 변수
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("zipcode").value = data.zonecode; //우편번호
                document.getElementById("address01").value = roadAddr;// 도로명 주소
            }
        }).open();
    }
    </script>
<%@ include file="./include/footer.jsp"  %>