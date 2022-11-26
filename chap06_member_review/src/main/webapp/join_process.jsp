<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 순서
	1. 데이터받아야 한다.
	2. db연결을 한다(커넥션).
	3. sql을 sqldeveloper에서 테스트 후 가져온다.
	4. pstmt에 ?에 대응하는 값을 세팅한다.
	5. db에 값을 던지고 결과를 받는다.
--%>
<!-- 
	기본 사용법
 1)getParameter로 넘어오는 값은 전부 문자이다 
 2)내장객체 셋 어프리트로 저장하고 불러오는 것은 오브젝트로 넘어왔다 -->
<%
	request.setCharacterEncoding("UTF-8");	// 한글로 넘어오기 때문에 입력(post가 쉽게 깨지는 현상이 발생(get은 그나마 무난))
	String user_id = request.getParameter("user_id"); // String user_id에 넘어온 정보 저장
	String user_pw = request.getParameter("user_pw");
	String user_name = request.getParameter("user_name");
	String user_email = request.getParameter("user_email");
	String user_phone = "";
	String user_phone_first = request.getParameter("user_phone_first");
	String user_phone_middle = request.getParameter("user_phone_middle");
	String user_phone_last = request.getParameter("user_phone_last");
	user_phone = user_phone_first+"-"+user_phone_middle+"-"+user_phone_last;
	int user_zipcode = Integer.parseInt( request.getParameter("zipcode") ); // db의 입력 값이 number로 되어 있어 형 변환을 해야 한다
	String user_address = "";
	String address01 = request.getParameter("address01");
	String address02 = request.getParameter("address02");
	user_address = address01+address02;
	// 여기까지 넘어온 값 처리 / 데이터 값 받아오기 완료
	// 시작전 WEB-INF -> lib에 ojdbc5.jar 넣어두기
	
	// db연결 시작 / 나중엔 자바 클래스로 빼서 사용할 부분
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "TIS002";
	String pw = "1234";
	
	Connection conn = null;
	PreparedStatement pstmt = null;//(준비된 문장)
	try {
		Class.forName(driver); // 클래스를 찾아온다(리플랙션)
		conn = DriverManager.getConnection(url, id, pw); // 드라이버 연결
		System.out.print("db연결");  // connection pool - 미리 연결 객체를 만들어 두고 사용 하는 것
		String sql = "INSERT INTO MEMBER VALUES (SEQ_MEMBER.NEXTVAL,?,?,?,?,?,?,?,SYSDATE)";// 문장 만들기
		pstmt = conn.prepareStatement(sql);//준비된 문장 연결
		// 값 세팅
		pstmt.setString(1, user_id);
		pstmt.setString(2, user_pw);
		pstmt.setString(3, user_name);
		pstmt.setString(4, user_email);
		pstmt.setString(5, user_phone);
		pstmt.setInt(6, user_zipcode);
		pstmt.setString(7, user_address);
		int result = pstmt.executeUpdate(); // 영향을 미친 행의 갯수 반환
		if(result > 0) {
			//입력 제대로 됐을때 로직
			response.sendRedirect("/chap06_member_review");
		} else {// 입력이 제대로 되지 않았을때
	%> 
		<script>
			alert("회원가입이 되지 않았습니다.");
			history.back();
		</script>
	<%
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if(pstmt!=null) pstmt.close();
		if(conn!=null) conn.close();
	}
	
%>





<!-- 넘어오는 것을 확인하고 싶으면 이런 식으로 하거나 sysout둘 중 하나로 테스트 할 수있다
${param.user_id } 
${param.user_pw } 
${param.user_name } 
${param.user_phone_first }
--> 