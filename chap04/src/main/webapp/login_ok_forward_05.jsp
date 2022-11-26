<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here02</title>
</head>
<body>
<!-- 자바와 html 같이 쓰는 법 -->
	<%
		int age =  Integer.parseInt( request.getParameter("user_age") );
		//조건
		if(age<=19) {
			//여기에 out.plitln을 사용해도 상관은 없다
	%>
			<script>
				alert("19세 미만은 입장 금지입니다.");
				history.back();
			</script>
	<%		
		} else {
			//request가 가지고 있는 속성으로 값을 세팅한다
			request.setAttribute("user_name", "장성호");
			request.setAttribute("user_age", age);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login_success_forward_05.jsp"); // 처리할 페이지 - 발송자 요청
			/// 페이지로 가지는 않고(url이동 안함) 페이지의 값만 보여준다 - 서버 내부적으로 처리된 것
			dispatcher.forward(request, response); //이게 하나의 패턴
		}
	%>
	
</body>
</html>