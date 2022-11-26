<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");//들어오는 데이터 한글 깨짐 방지 (받고) 
	response.setContentType("text/html; charsset=UTF-8");//나가는 데이터가 깨짐 방지(내보내고)
	// 한글로 넘어올 것이니 세팅
	
	// 데이터받아오기
	String user_id = request.getParameter("user_id"); // name값을 받는 것 
	String user_pw = request.getParameter("user_pw");
	String user_name = request.getParameter("user_name");
	String user_email = request.getParameter("user_email");
	String user_phone = ""; // 미리 빈칸 생성
	String user_phone_first = request.getParameter("user_phone_first");
	String user_phone_middle = request.getParameter("user_phone_middle");
	String user_phone_last = request.getParameter("user_phone_last");
	user_phone = user_phone_first+user_phone_middle+user_phone_last; // 받은 것을 더해주기
	String address = "";
	int zipcode = Integer.parseInt(request.getParameter("zipcode"));
	String address01 = request.getParameter("address01");
	String address02 = request.getParameter("address02");
	address = address01+" "+address02;
	System.out.print(zipcode);
	
	/* 1.드라이버 연결 */
	String driver = "oracle.jdbc.OracleDriver"; 
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "TIS002";
	String pw = "1234";
	
	//2. injection(해킹기법 = 방지차원에서  PreparedStatement 사용)
	Connection conn = null; // 연결할 것
	PreparedStatement pstmt = null;
	
	String sql =  "INSERT INTO MEMBER VALUES(SEQ_MEMBER.NEXTVAL,?,?,?,?,?,?,?,SYSDATE)";
	
	//3. 클래스를 찾아서 연결
	try{
		Class.forName(driver);// 드라이버 이름을 forName으로 찾을 수 있다
		conn = DriverManager.getConnection(url,id,pw); // 연결을 잡는 것
		pstmt = conn.prepareStatement(sql); // 미리 쿼리 날려두기 위에 미리 변수로 만든 sql 사용
		pstmt.setString(1, user_id);
		pstmt.setString(2, user_pw);
		pstmt.setString(3, user_name);
		pstmt.setString(4, user_email);
		pstmt.setString(5, user_phone);
		pstmt.setInt(6, zipcode);
		pstmt.setString(7, address);
		int result = pstmt.executeUpdate(); // 몇개에 영향을 미쳤는지 / 갯수 반환
		if(result > 0) { //입력이 하나 이상 되었다
			response.sendRedirect("list.jsp"); // 입력한 값을 list.jsp로 이동 // sendRedirect(데이터를 바꿔주는 것)
		} else {
%>			
			<script>
				alert("회원가입에 실패하였습니다");history.back();
			</script>		//db오류
<%			
		}
	} catch (Exception e){
		e.printStackTrace();
	} finally{ // 무조건 실행
			if(pstmt!=null) pstmt.close(); // null이 아니라면 닫아주기
			if(conn!=null) conn.close();
	}
	
%>