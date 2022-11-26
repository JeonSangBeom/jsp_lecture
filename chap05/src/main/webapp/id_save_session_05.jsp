<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String loginID="";
	String isChecked = "";
	Cookie cookies[] = request.getCookies();
	if(cookies!=null) {
		for(Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if(cookieName.equals("loginID")) {
				loginID = cookie.getValue();
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
	if(!loginID.equals("")) isChecked = "checked";
	
	// 로그인시
	// 리퀘스트로 할 경우 포워드에 저장하고 해야한다
	// 쿠키로 하면 복잡
	// 세션이 편리(끊어지기 전까지는 사이트 내에서 돌아다닐 수 있다)
			
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ID 저장해두기 03</h1>
	<form action="id_save_session_process_05.jsp" method="post"> <!-- // 클릭시id_save_session_process.jsp 로 이동 -->
		<div>
			<label>
				<span>아이디</span>
				<input type="text" name="user_id" value="<%= loginID %>" placeholder="아이드를 입력하세요.">
			</label>
			<label><input type="checkbox" name="save_check" value="yes" <%= isChecked %>>아이디 저장하기</label>
		</div>
		<div>
			<label><span>패스워드</span><input type="password" name="user_pw" placeholder="비밀번호 입력하세요."></label>
		</div>
		<div><button>로그인</button></div>
	</form>
</body>
</html>