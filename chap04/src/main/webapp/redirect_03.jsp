<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>redirect는 그냥 페이지를 다른데로 넘겨주는 역할만 합니다.</p>
	
	<%
		String name = "다음"; //넘어온 데이터 가 "다음" 이면
		if(name.equals("다음")) response.sendRedirect("http://www.daum.net");// sendRedirect 사이트로 이동
		else response.sendRedirect("http://www.naver.com"); //아닐경우 네이버로 이동
		
		//다른 방법
		//out.println("<script>alert('로그인 되었습니다.');location.href='http://www.daum.net'</script>");
		// 로그인 되었다는 경고창을 낸 후 다음 사이트로 이동
		
	%>
</body>
</html>