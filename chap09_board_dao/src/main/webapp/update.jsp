<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp"%>
<main>
	<div class="inner">
		<h2 class="subTitle">WRITE</h2>
		<div id="contents">
			<!-- 데이터 받아서 처리하기....forward방식으로 데이터 보여주기....  -->
			<form method="POST" action="UpdateProcess.do" id="join" class="form">
				<table>
					<colgroup>
						<col style="width: 20%">
						<col style="width: 80%">
					</colgroup>
					<tbody>
						<tr>
							<th>name <span class="required">*</span></th>
							<td><input type="text" name="user_name" id="user_name"
								placeholder="이름를 입력하세요." value="${boardDto.name }"></td>
						</tr>
						<tr>
							<th>subject <span class="required">*</span></th>
							<td><input type="text" name="user_subject" id="user_subject"
								placeholder="제목을 입력하세요." value="${boardDto.subject }"></td>
						</tr>

						<tr>
							<th>email <span class="required">*</span></th>
							<td><input type="text" name="user_email" id="user_email"
								placeholder="메일을 입력하세요." value="${boardDto.email }"></td>
						</tr>
						<tr>
							<th>패스워드 <span class="required">*</span></th>
							<td><input type="password" name="user_pw" id="user_pw"
								placeholder="비밀번호를 입력하세요."></td>
						</tr>
						<tr>
							<th>contents<span class="required">*</span></th>
							<td><textarea placeholder="내용을 입력하세요." name="user_contents"
									id="summernote">${boardDto.contents }</textarea></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="no" value="${param.no }">
				<div class="btns">
					<button type="submit" class="btn btnConfirm">수정</button>
					<button type="reset" class="btn btnCancel">취소</button>
					<a href="List.do" class="btn btnCancel">글목록</a>
				</div>
			</form>
		</div>
	</div>
</main>
<script>
	let idCheck = false;
	const korean = /[ㄱ-ㅎㅏ-ㅣ가-힣]/g;
	const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;//이메일 정규식
	$(".btnZipcode").on("click", function() {
		postCode();
		return false;
	});
	// form을 통해서 보낼 수 있다. 
	// <a href="join_process.jsp?user_id=hulk">보내기</a>
	// ajax로 데이터 보낼 수 있다.
	$(".btnIdCheck").on("click", function(e) {
		//e.preventDefault();
		const user_id = $("#user_id").val();
		if (user_id === "") {
			alert("아이디를 입력해 주세요.");
		}
		$.ajax({
			url : "id_check02.jsp",
			type : "POST",
			data : {
				"user_id" : user_id
			},
			success : function(res) {
				console.log(res);
				if (res.count > 0) {
					alert("사용할 수 없는 아이디입니다.");
					$("#user_id").val("");
					$("#user_id").focus();
				} else {
					//alert("사용할 수 있는 아이디입니다.");
					const yes = confirm("사용가능한 아이디입니다. 사용하시겠어요");
					if (yes) {
						$("#user_id").attr("readonly", true);
						$("#user_id").addClass("readonly");
						idCheck = true;
					} else {
						$("#user_id").val("");
					}
				}
			}
		});
		return false;
	});
	/*
	$("#btnIdCheck").on("click",function(e){
		e.preventDefault();
		const user_id = $("#user_id").val();
		$.ajax({
			url:"id_check.jsp",
			type:"POST",
			data:{"user_id":user_id},
			success:function(res){
				console.log(res.count);
				if(res.count) {
					alert("사용할 수 없습니다.")
				}else {
					alert("사용할 수 있습니다.");
				}
			}
		});
		return false;
	});
	 */
	/*
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
		} else if(!email.test($("#user_email").val())) {
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
		} else if(!idCheck) {
			alert("아이디 체크를 해주세요");
			return false;
		}
	});
	 */
	function postCode() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				let roadAddr = data.roadAddress; // 도로명 주소 변수
				let extraRoadAddr = ''; // 참고 항목 변수
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', '
							+ data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}
				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById("zipcode").value = data.zonecode;
				document.getElementById("address01").value = roadAddr;
			}
		}).open();
	}
	//summernote 적용
	$("#summernote").summernote({
		height : 300,
		callbacks : {
			onImageUpload : function(files) {
				/* uploadImage(files[0],this); - 하나씩 올릴 수 있는 방법 */
				for(let i=0;i<files.length;i++){
					uploadImage(files[i],this);	
				}// 여러개 첨부하여 사용할 경우
				// 이미지 업로드  
			}
		}
	});
	// 데이터 전달할때 사용한 것들
	//1) queryString  ===>       view.do?img=aaa&text=bbb   (get)
	//2) form 태그를 이용하는 방법   <form method="GET 또는 POST" action="넘길 페이지"></form>
	//3) FormData(form과 비슷) - 아래 방식
	function uploadImage(file,editor) { // uploadImage함수 생성
		const sendData = new FormData(); // 넘길 데이터는 FormData 생성
		sendData.append("uploadFile",file); // "uploadFile"( 이건 아무거나 써도 상관 없다 file매개변수
				//<input type="file" name"uploadFile"> 과 같은 것
		$.ajax({
			url:"SummerNoteFileUpload.do",
			type:"POST", // 안쓰면 디폴트가 깨진다
			data:sendData, // FormDate로 만든 sendData
			contentType:false,// 꼭 써야 하는 것  
			//안쓰게 되면 이렇게 application/x-www-form-urlencode로 넘어온다(form데이터가 넘어가지 않는다)
			// false로 해야지만 multipart/form-data로 날아간다
			processData:false,// 꼭 써야 하는 것
			dataType:"json", // 넘어오는 데이터 타입을 json으로 받기 원한다
			success:function(data) {
				console.log(data);
				$(editor).summernote("editor.insertImage",data.url); // summernote호출법
				//editor.insertImage - 에디터에 이미지를 집어 넣는데 data.url(이 url은 섬머노트파일업로드에서 uploadPath의 값을 넣은 url을 뜻한다) 
				//                                                             - ajax로 리턴된 ajax에 있는 url값을 이미지로 집어 넣겠단 뜻
			},
			error:function(){
				alert("파일 업로드 실패");
			}
		})
	}
</script>
<%@ include file="./include/footer.jsp"%>





