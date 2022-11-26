<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp"%>
<%
	//1. 드라이버 연결
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "TIS002";
	String pw = "1234";
	
	//2. Injection 
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 세션 값 받아오기 (id)
	String sessionId = (String)session.getAttribute("id");
	System.out.print(sessionId);
%>

<main>
	<div class="container">
		<h2 class="subTitle">MEMBER LIST</h2>
		<div id="contents">
			<div class="tableBox">
				<table>
					<colgroup>
						<col style="width: 150px">
						<col>
					</colgroup>
					<tbody>
						<%
							String user_id = request.getParameter("user_id");// list 에서 ?쿼리스트링으로 받아온 user_id 입력
							String sql =  "SELECT * FROM MEMBER WHERE ID = ?"; 
							try{
								Class.forName(driver);
								conn = DriverManager.getConnection(url,id,pw);
								pstmt = conn.prepareStatement(sql);//conn을 가지고 prepareStatement를 미리 쿼리를 날려 놓기 pstmt에
								pstmt.setString(1, user_id);//? 정보 넣어두기
								rs = pstmt.executeQuery(); //쿼리 보이게
								while(rs.next()) {// 다음게 있을때 트루를 반환한다
									out.print("<tr>");
										out.print("<th>ID</th>");
										out.print("<td>"+rs.getString("id")+"</td>");
									out.print("</tr>");
									out.print("<tr>");
										out.print("<th>NAME</th>");
										out.print("<td>"+rs.getString("name")+"</td>");
									out.print("</tr>");
									out.print("<tr>");
									out.print("<th>E-MAIL</th>");
									out.print("<td>"+rs.getString("email")+"</td>");
									out.print("</tr>");
									out.print("<tr>");
									out.print("<th>PHONE</th>");
									out.print("<td>"+rs.getString("phone")+"</td>");
									out.print("</tr>");
									out.print("<tr>");
									out.print("<th>ZIP-CODE</th>");
									out.print("<td>"+rs.getString("zipcode")+"</td>");
									out.print("</tr>");
									out.print("<tr>");
									out.print("<th>ADDRESS</th>");
									out.print("<td>"+rs.getString("address")+"</td>");
									out.print("</tr>");
								}
							} catch (Exception e) {// 통으로 받기
								e.printStackTrace();
							} finally {
								if(rs!=null) rs.close();
								if(pstmt!=null) pstmt.close();
								if(conn!=null) conn.close();
							}
						%>
					</tbody>
				</table>
				<div class="btns">
					<a href="update.jsp" class="btn btnConfirm">회원정보 수정</a>
					<a href="delete.jsp?user_id=<%=sessionId %>" class="btn btnCancel">회원탈퇴</a>
					<!-- 기본적인 로직은 로그인 후 회원 탈퇴이다 / user_id<//sessionId > 쓰지 않아도 작동한다 -->
				</div>
			</div>
		</div>
	</div>
</main>

<%@ include file="./include/footer.jsp"%>