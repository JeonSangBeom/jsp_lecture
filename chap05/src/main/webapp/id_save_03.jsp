<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String loginID=""; // 처음 시작시 공백으로 두기
	String isChecked = "";
	Cookie cookies[] = request.getCookies();// 쿠키 값 읽기
	if(cookies!=null) { // 널이 아니라면
		for(Cookie cookie : cookies) { // 쿠키는 쿠키셋 읽기
			String cookieName = cookie.getName(); // cookieName에 쿠키 이름을 받아오고 
			if(cookieName.equals("loginID")) { // 쿠키 네임과 로그인 아이디가 같으면
				loginID = cookie.getValue();//쿠키에 지정된 값으로 로그인 아이디에 사용한다(아이디 저장하기)
			}
		}
		/*
		for(int i=0;i<cookies.length;i++) {
			String cookieName = cookies[i].getName();
			if(cookieName.equals("loginID")) {
				loginID = cookies[i].getValue();
			}
		}
		*/
	}
	if(!loginID.equals("")) isChecked = "checked";//loginID(이게 있다면 로그인이 되었다는 것이기 때문에)가 공백이 아니라면  checked 해두겠다
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here01</title>
</head>
<body>
	<h1>ID 저장해두기</h1>
	<form action="id_save_process_03.jsp" method="post"> 
	<!-- action으로 넘어가기 -->
		<div>
			<label>
				<span>아이디</span>
				<input type="text" name="user_id" value="<%= loginID %>" placeholder="아이드를 입력하세요.">
				<!--<%= loginID %> - 체크 후에 값이 저장이 된 상태로 사용되기 때문에 위에 지정된 자바의 성질에 따라 공백 아니면 아이디가 표시된다  -->
			</label>
			<label><input type="checkbox" name="save_check" value="yes" <%= isChecked %>>아이디 저장하기</label>
			<!-- yes 뒤에 checked를 입력하면 체크가 된 상태를 볼 수 있다 
				체크 시에 yes란 값이 들어온다-->
		</div>
		<div>
			<label><span>패스워드</span><input type="password" name="user_pw" placeholder="비밀번호 입력하세요."></label>
		</div>
		<div><button>로그인</button></div>
	</form>
</body>
</html>