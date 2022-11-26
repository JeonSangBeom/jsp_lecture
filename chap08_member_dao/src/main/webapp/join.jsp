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
		  					<td>
		  						<input type="text" name="user_id" id="user_id" placeholder="아이디를 입력하세요."
		  							style="width:calc(100% - 100px)"
		  						>
		  						<button class="btn btnIdCheck" id="btnIdCheck">ID중복체크</button>
		  					</td>
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
		  					<th>이메일 <span class="required">*</span></th>
		  					<td><input type="text" name="user_email" id="user_email" placeholder="메일을 입력하세요."></td>
		  				</tr>
		  				<tr>
		  					<th>전화번호 <span class="required">*</span></th>
		  					<td>
		  						<select name="user_phone_first" class="short">
		  							<option value="010" selected>010</option>
		  							<option value="011">011</option>
		  							<option value="017">017</option>
		  						</select>
		  						<input type="text" name="user_phone_middle" class="short" id="user_phone_middle" placeholder="전화번호를 입력하세요.">
		  						<input type="text" name="user_phone_last" class="short" id="user_phone_last" placeholder="전화번호를 입력하세요.">
		  					</td>
		  				</tr>
		  				<tr>
		  					<th>주소 <span class="required">*</span></th>
		  					<td>
		  						<div>
		  							<input type="number" name="zipcode" placeholder="우편번호" class="short" id="zipcode" readonly>
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
    	let idCheck = false; // 아이디 중복 체크 여부 설정 시 미리 지정
    	const korean = /[ㄱ-ㅎㅏ-ㅣ가-힣]/g;
    	const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;//이메일 정규식
    	$(".btnZipcode").on("click",function(){
        	postCode();
        	return false;
        });
    	
    	// 데이터 보내는 방법 3가지
    	// form을 통해서 보낼 수 있다. (action, name)
    	// <a href="join_process.jsp?user_id=hulk">보내기</a> - get방식
    	// ajax로 데이터 보낼 수 있다.
    	/*
    	$(".btnIdCheck").on("click",function(e){ //btnIdCheck 클래스나 아이디나 상관 없이 찾은 것
    		//e.preventDefault(); -> return false 대신 사용 가능(function(e) 'e' 입력)
    		const user_id = $("#user_id").val(); // 클릭하면 user_id라는 변수를 받아서 #user_id에 있는 값을 넣어둔다
    		if(user_id==="") { // 만약 공백이라면
    			alert("아이디를 입력해 주세요.");
    		}
    		$.ajax({
    			url:"id_check02.jsp", // 보낼 곳
    			type:"POST",// 타입(메서드 get과 post)
    			data:{"user_id":user_id},// 실어 보낼 것 - 오브젝트 형식 / 위에 변수로 입력된 값 / "user_id"안에는 아무거나 써도 상관없다 사용자 정의
    			success:function(res){// 응답을 받았을때
    				console.log(res);
    				if(res.count > 0) { // res.count : 넘어온 값
    					alert("사용할 수 없는 아이디입니다.");
    					$("#user_id").val(""); // 공백처리 (초기화)
    					$("#user_id").focus();
    				} else {// 그렇지 않다면
    					//alert("사용할 수 있는 아이디입니다.");
    					const yes = confirm("사용가능한 아이디입니다. 사용하시겠어요");//confirm : 확인창이 뜨게 할수 있다(true false)
    					if(yes) { //yes일 때 할 일
    						$("#user_id").attr("readonly",true);//attr:속성 (readonly:읽기전용)
    						$("#user_id").addClass("readonly"); // 클래스 넣기 scss에서 form 꾸며주기 위해
    						idCheck=true;
    					} else {
    						$("#user_id").val("");	
    					}
    				}
    			}
    		});
    		return false; // 폼 안에 있는 데이터를 보내고 멈추기 위해 적어 두기
    	});
    	
    	*/
    	
    	
    	
    	
    	$(".btnConfirm").on("click",function(){
    		if($("#user_id").val()=="") {
    			alert("ID를 입력해 주세요");
    			$("#user_id").focus();
    			return false;
    		} else if(korean.test($("#user_id").val())){
    			alert("id에 한글을 쓸 수 없습니다.");
    			$("#user_id").val("");
    			$("#user_id").focus();
    			return false;
    		} else if($("#user_pw").val()=="") {
    			alert("password를 입렫해 주세요");
    			$("#user_pw").focus();
    			return false;
    		} else if($("#user_pw_confirm").val()=="") {
    			alert("password 확인을 입렫해 주세요");
    			$("#user_pw_confirm").focus();
    			return false;
    		} else if($("#user_pw").val()!==$("#user_pw_confirm").val()) {
    			alert("패스워드가 맞지 않습니다.");
    			$("#user_pw_confirm").focus();
    			$("#user_pw_confirm").val("");
    			return false;
    		} else if($("#user_name").val()=="") {
    			alert("name을 입렫해 주세요");
    			$("#user_name").focus();
    			return false;
    		} else if($("#user_email").val()=="") {
    			alert("email을 입력해 주세요");
    			$("#user_email").focus();
    			return false;
    		} else if(!email.test($("#user_email").val())) { //email.test라는 정규표현식 메서드를 사용하여 값과 비교하는 것
    			alert("이메일을 형식에 맞게 입력해 주세요.");
    			$("#user_email").focus();
    			return false;
    		} else if($("#user_phone_middle").val()==="") {
        		alert("전화번호를 입력하세요.");
        		$("#user_phone_middle").focus();
        		return false;
        	} else if($("#user_phone_last").val()==="") {
        		alert("전화번호를 입력하세요.");
        		$("#user_phone_last").focus();
        		return false;
        	} else if($("#zipcode").val()=="") {
    			alert("우편번호를 입렫해 주세요");
    			$("#user_zipcode").focus();
    			return false;
    		} else if($("#user_address01").val()=="") {
    			alert("주소를 입렫해 주세요");
    			$("#user_address01").focus();
    			return false;
    		} else if(!idCheck) {// idcheck가 참이 아니라면
    			alert("아이디 체크를 해주세요");
    			return false;
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
                    document.getElementById("zipcode").value = data.zonecode;
                    document.getElementById("address01").value = roadAddr;
                }
            }).open();
        }
    	
    	
    </script>
<%@ include file="./include/footer.jsp"  %>

