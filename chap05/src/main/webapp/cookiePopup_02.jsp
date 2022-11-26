<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% //java를 이렇게 위에 써도 상관없다 
	String popupOn = "on";  // 변수 하나 생성 - off가 되면 소스 자체에 값이 안나타나는 것이다(팝업창 생성 여부도 결정)
	
	String oneDay =  request.getParameter("oneDay");// ajax 보낸 oneDay값을 getParameter로 받는다	
	System.out.println("클라이언트에서 ajax로 요청보낸 값 oneDay : "+oneDay); // 오늘하루 이창 클릭시 off값을 받아온다
	
	if(oneDay!=null && oneDay.equals("off")) {// oneday가 null이 아니고 off라면
		Cookie cookie = new Cookie("popupClose","off");// 쿠키에 popupClose 생성
		cookie.setPath(request.getContextPath());//setPath(어디서 유지시킬지 설정)은 request(요청 보낸것에)getContextPath(chap05에서만 유지)
		cookie.setMaxAge(60*60*24);// 하루 설정
		response.addCookie(cookie);// 쿠키를 보내 주겠다
	}
	Cookie cookies[] = request.getCookies(); // 쿠키 배열로 읽을 것 생성
	if(cookies!=null) {
		for(Cookie cookie : cookies) {
			String cookieName =  cookie.getName();
			String cookieValue =  cookie.getValue();
			out.println(cookieName +" : "+cookieValue+"<br>");
			if(cookieName.equals("popupClose")) { // 쿠키 이름 중 popupClose 가 있으면
				popupOn = cookieValue; // 변수 popupOn은 쿠키 value를 읽기(위에 설정한 popupClose이란 name의 value값 off )
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here 01</title>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/layout.css" />
<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
	
	<h1>쿠키를 통한 팝업 띄우기...</h1>
	<%
		if(popupOn.equals("on")) { // popupOn이 on일 때에만 아래 html을 사용하겠다
	%>
		<div id="popup">
			<div class="contents">
				<h2>Notice</h2>
				<p>공지사항입니다.</p>
				<p>공지사항입니다.</p>
				<p>공지사항입니다.</p>
				<p>공지사항입니다.</p>
				<p>공지사항입니다.</p>
				<p>공지사항입니다.</p>
				<p>공지사항입니다.</p>
				<p>공지사항입니다.</p>
			</div>
			<div class="btns">
				<button class="btn btnOneday">오늘 하루 이창을 열지 않기</button>
				<button class="btn btnClose">닫기</button>
			</div>
		</div>
	<% } %>
	<script>
		$(".btnClose").on("click",function(){
			$("#popup").hide();
		});
		$(".btnOneday").on("click",function(){
			$("#popup").hide();
			$.ajax({ // 데이터 날리기
				url:"./cookiePopup_02.jsp",// url을 cookiePopup.jsp에 보내겠다(off 값을 여기서 받는다)
				type: "get", // 타입은 get 방식 굳이 안써도 상관은 없다(디폴트가 get방식이기 때문)
				data: {oneDay:"off"}, //data로 오브젝트 실어 보내기 - oneDay(name으로 보면 편하다)에 off라는 값을 실어 보내기
				success:function(res){
					//if(res!=="") location.reload(); // 리로드 - 새로고침 한다는 것 / 여기선 굳이 필요없다
				}
			})
		});
		
	</script>
</body>
</html>