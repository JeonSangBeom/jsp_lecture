package com.jjang051.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;



public class JDBCConnection {
	public Connection conn;
	public PreparedStatement pstmt;
	public ResultSet rs;
	private String driver = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "TIS001";
	private String pw = "1234";
	
	public JDBCConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,id,pw);
			System.out.println("연결 성공01");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public JDBCConnection(String driver,String url,String id,String pw) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,id,pw);
			System.out.println("연결 성공02");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public JDBCConnection(ServletContext application) {
		try {
			String driver = application.getInitParameter("OracleDriver");
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pw = application.getInitParameter("OraclePassword");
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url,id,pw);
			System.out.println("연결 성공03");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void close() {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
}





