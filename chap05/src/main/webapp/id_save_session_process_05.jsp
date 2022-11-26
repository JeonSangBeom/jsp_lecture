<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
	String save_check = request.getParameter("save_check");
	System.out.println(user_id+"==="+user_pw+"==="+save_check);
	if(user_id.equals("jjang051") && user_pw.equals("1234")) {
		if(save_check!=null && save_check.equals("yes")) {
			//쿠키세팅....
			//"loginID","jjang051"
			Cookie cookie = new Cookie("loginID",user_id);
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("loginID","");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}// 쿠키 저장하는 곳
		//로그인 되고 ->
		session.setAttribute("login_user", user_id); //세션 세팅 (로그인을 하면 데이터가 만들어 진다)
		out.println("<script>alert('로그인 되었습니다.'); location.href='main_05.jsp';</script>");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02</title>
</head>
<body>

</body>
</html>