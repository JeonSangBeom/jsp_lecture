package com.jjang051.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnection {
	private String driver = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "TIS002";
	private String pw = "1234";
	
	
	//외부로 노출
	public Connection conn;
	public PreparedStatement pstmt;
	public ResultSet rs;
	public JDBCConnection() {//1) alt+sh+s -> constructors from superclass -> 생상
		try {
			Class.forName(driver); //2) 만들고 try catch
			conn = DriverManager.getConnection(url, id, pw); //3)입력후 try catch
			System.out.println("DB 연결 성공");
		} catch (Exception e) { // 4)catch하나 지우고 exception으로 처리 
			e.printStackTrace();
		} 
	}
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}