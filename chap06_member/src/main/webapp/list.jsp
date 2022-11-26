<%@page import="java.sql.ResultSet"%>
<%@page import="javax.naming.spi.DirStateFactory.Result"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp"%>
<%
	/* 1.드라이버 연결 - 데이터베이스를 가져오기 위해*/
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "TIS002";
	String pw = "1234";
	
	//2. injection(해킹기법)
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	//select에는 resultset이 있어야한다
		
%>
 	
	

<main>
	<div class="container">
		<h2 class="subTitle">MEMBER LIST</h2>
		<div id="contents">
			<div class = "tableBox">
			<table>
				<colgroup> <!-- 테이블 사이즈 설정 ,-->
					<col style="width:30px">
					<col style="width:100px">
					<col style="width:100px">
					<col style="width:200px">
					<col style="width:150px">
					<col style="width:100px">
					<col> <!-- 안들어가면 자동으로 들어간다 -->
					<col style="width:200px">
				</colgroup>	
				<thead>
					<tr>
						<th>NO</th>
						<th>ID</th>
						<th>NAME</th>
						<th>EMAIL</th>
						<th>PHONE</th>
						<th>우편번호</th>
						<th>주소</th>
						<th>DATE</th>
					</tr>
				</thead>
				<tbody>
				<%
					String sql =  "SELECT * FROM MEMBER"; 
					try{
						Class.forName(driver);
						conn = DriverManager.getConnection(url,id,pw);
						pstmt = conn.prepareStatement(sql); // 따로 ? 표가 없어 바로 날라간다
						rs = pstmt.executeQuery(); // 몇개에 영향을 미쳤는지 / 갯수 반환
						//select만 제외한 나머지만 업데이트이고 select는 query 이다
						//executeupdate는 정수를 리턴하지만 select는 그냥 rs가 된다
						while(rs.next()) {
							//반복 구간
							out.print("<tr>");
								out.print("<td>"+rs.getString("no")+"</td>");
								out.print("<td><a href='member_info.jsp?user_id="+rs.getString("id")+"'>"+rs.getString("id")+"</a></td>");
								//a태그로 넘기는 것은 get 방식이다
								out.print("<td>"+rs.getString("name")+"</td>");
								out.print("<td>"+rs.getString("email")+"</td>");
								out.print("<td>"+rs.getString("phone")+"</td>");
								out.print("<td>"+rs.getInt("zipcode")+"</td>"); //int를 써도 들어간다 (숫자만)
								out.print("<td>"+rs.getString("address")+"</td>");
								out.print("<td>"+rs.getString("regdate")+"</td>	");					
							out.print("</tr>");
}
					} catch (Exception e){
						e.printStackTrace();  
					} finally{
							if(pstmt!=null) pstmt.close();
							if(conn!=null) conn.close();
					}
				%>
					
				</tbody>
			</table>
		</div>
		</div>
	</div>
</main>	
<%@ include file="./include/footer.jsp"%>
