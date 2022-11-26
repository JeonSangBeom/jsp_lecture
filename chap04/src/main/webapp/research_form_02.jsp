<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here01</title>
</head>
<body>
	<form method="get" action="research_ok_02.jsp">
	<!-- 폼에는 반드시 두개가 들어간다 action - jsp파일에서도 받아서 처리 가능 -->
		<table border="1" width="600px">
			<tr>
				<th>이름</th>
				<td><input type="text" name="user_name" placeholder="이름을 적으세요."></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<label><input type="radio" name="gender" value="남자">남자</label>
					<label><input type="radio" name="gender" value="여자">여자</label>
				</td>
			</tr>
			<tr>
				<th>좋아하는 계절</th>
				<td>
					<label><input type="checkbox" name="season" value="spring">봄</label>
					<label><input type="checkbox" name="season" value="summer">여름</label>
					<label><input type="checkbox" name="season" value="fall">가을</label>
					<label><input type="checkbox" name="season" value="winter">겨울</label>
				</td>
			</tr>
		</table>
		<button type="submit">전송</button><!--디폴트가 submit이다  -->
		<button type="reset">취소</button>
	</form>
</body>
</html>