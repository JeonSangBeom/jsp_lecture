<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//값 받아오기
	String user_id = request.getParameter("user_id");// id_save에서 name로 설정해둔 값 받아오기
	String user_pw = request.getParameter("user_pw");
	String save_check = request.getParameter("save_check");	
	System.out.println(user_id+"==="+user_pw+"==="+save_check);
	
	if(user_id.equals("jjang051") && user_pw.equals("1234")) { // 두가지 값이 제대로 들어왔을때 
		if(save_check!=null && save_check.equals("yes")) { // 저장한 값이 null이 아니고(안써두면 오류 발생) yes란 값이 들어와 있을때
			//쿠키세팅 시작 - 아이디 저장 정보
			//"loginID","jjang051"
			Cookie cookie = new Cookie("loginID",user_id); //loginID에 값은 user_id(넘어온 user id)로 쿠키 생성
			cookie.setPath("/");// setPath 적어도 안적어도 상관은 없다 '/' - 루트 path(경로)
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);//쿠키에 보내기
		} else {// 그렇지 않다면 // yes가 설정 안될때
			Cookie cookie = new Cookie("loginID",""); // 유저 아이디 안보이게 공백으로 해두기
			cookie.setPath("/");
			cookie.setMaxAge(0);// 쿠키값 없애줄때(따로 지우는 것은 없다)
			response.addCookie(cookie);
		}
		out.println("<script>alert('로그인 되었습니다.'); location.href='id_save_03.jsp';</script>");
	}
	//쿠키는 로컬에 저장되는 것 (클라이언트에서 간섭이 올 수 있다) - 해당 로그인 정보(접근에 제한이 필요한 정보)는 세션에 저장해야 한다
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>