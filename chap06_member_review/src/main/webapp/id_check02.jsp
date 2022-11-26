<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.jjang051.jdbc.JDBCConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	JDBCConnection jdbcConnection = new JDBCConnection();// 연결해주기
	Connection conn  = jdbcConnection.conn;
	PreparedStatement pstmt = jdbcConnection.pstmt;
	ResultSet rs = null;
	
	//쿼리 받기
	String user_id = request.getParameter("user_id"); // join에서 ajax로 지정한 user_id 를 받아오기
	System.out.println("ajax로 넘어온 값==="+user_id); // join에 정해둔 
	
	String sql =  "SELECT COUNT(*) AS COUNT FROM MEMBER WHERE ID = ?";
	// 카운트 함수로 있는지 없는지 판단	
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1,user_id);
	rs = pstmt.executeQuery();
	int result = 0;
	if(rs.next()) { // 하나만 날라가서 if로 사용해도 무관
		result = rs.getInt("count"); // 중복이 없으면 0, 중복이 있으면 1이상의 숫자가 나올거임..
		// 카운트(컬럼명)는 리턴 값이 숫자이기에 int로 받아주면 된다
	}
	
	// response.setContentType("text/html;charset=UTF_8"); //html로 결과 던지기
	// 자바 파일을 json으로 바꿔주는 라이브러리 사용 -  gson-2.8.9.jar lib에 넣어주기
	
	// json으로 결과 던지기(gson활용)
	response.setContentType("application/json; charset=UTF-8");// json으로 내보내겠단 선언
	Map<String, Integer> resultMap = new HashMap<>(); // 맵으로 만들어서
	resultMap.put("count", result);//(string / count)
	Gson gson = new Gson(); //선언 
	out.print(gson.toJson(resultMap)); // resultMap을 gson(java데이터를 -> json데이터로 바꿔주기)
	//out.print("{'aa':1}");
%>



