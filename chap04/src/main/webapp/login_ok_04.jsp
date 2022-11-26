<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- 핵심 로직 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here02</title>
</head>
<body>
	<%
	//기본 설정 - response는 필요 없다
	request.setCharacterEncoding("UTF-8"); // 넘어오는 값을 UTF-8로 처리
	//db_id(예시) - 변수 설정
	String db_id = "jjang051";
	String db_pw = "1234";
	String db_name = "장성호";
	//받아오기
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
	//조건
	if (user_id.equals(db_id) && user_pw.equals(db_pw)) {// 두개다 db와 입력 값이 맞을때		
		response.sendRedirect("login_success_04.jsp?user_name=" + URLEncoder.encode(db_name, "UTF-8"));
		//로그인 성공시 login_success으로 이동(sendRedirect) / ? - (쿼리스트링(데이터를 보낼때 사용되는 방법) 시작을 알리는 기호)
		//URLEncoder.encode(db_name, "UTF-8") 한글로 넘어갈떄 입력해 두어야 한다
		//-> URLEncoder(임폴트 필요)로 db_name을 전달(db_name, "UTF-8") db.name을 utf-8로 보내겠다 / 영어면 db_name만 써도 된다
		
		//request.getRequestDispatcher(path); //정보 보내는 다른 방법 중 하나
	} else {
		//history.go(-1); -- 이렇게 쓰기도 한다 (바로 전으로 돌아가기)
		out.println("<script> alert('아이디 또는 비밀번호가 맞질 많습니다.'); history.back(); </script>");
		//response.sendRedirect("login_form_04.jsp"); //이걸 사용시 alert는 없고 다시 입력하는 곳으로 이동
	}
	%>
</body>
</html>