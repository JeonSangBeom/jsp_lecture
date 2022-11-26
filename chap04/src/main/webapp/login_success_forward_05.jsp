<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here02</title>
</head>
<body>
	<%
	    //겟 파라미터는 매개변수로 왔다갔다 / 어트리버트는 속성으로 받아온다
		String user_name = (String) request.getAttribute("user_name"); //setAttribute로 받아 입력해 뒀기 때문에 getAttribute로 받아야 한다
		// getAttribute는 무조건 오브젝트로 넘어와서 스트링이나 int로 형변환을 해주어야 한다
		int user_age =  (int) request.getAttribute("user_age");
	%>
	<p>user_name : <%= user_name %></p>
	<p>user_age : <%= user_age %></p>
</body>
</html>