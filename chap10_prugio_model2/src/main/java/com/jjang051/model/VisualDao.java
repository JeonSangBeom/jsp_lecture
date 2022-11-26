package com.jjang051.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisualDao {
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "TIS002";
	String pw = "1234";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//집어넣는 것
	public int insertVisual(VisualDto visualDto) {// ViseualDto 를 visualDto매개변수로 받아서 int로 반환
		int result=0;
		try {
			getConnection();
			//db순서와 같아야 한다 :?, ?, ? 아래 순번과 다르면 오류 발생
			String sql = "INSERT INTO VISUAL VALUES (SEQ_VISUAL.NEXTVAL,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, visualDto.getVisualImg());
			pstmt.setString(2, visualDto.getVisualRealImg());
			pstmt.setString(3, visualDto.getSlogan());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return result; 
	}
	//가져오는 것
	public ArrayList<VisualDto> getAllVisual() {//VisualDto를 담는 ArrayList를 getAllVisual(받아오기)
		ArrayList<VisualDto> visualList = new ArrayList<>();
		try {
			getConnection();
			String sql = "SELECT * FROM VISUAL";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VisualDto visualDto = new VisualDto();
				visualDto.setNo(rs.getInt("no"));// 컬럼 No에서 정수를 가져오겠다no로
				visualDto.setSlogan(rs.getString("slogan"));
				visualDto.setVisualImg(rs.getString("visual_img"));
				visualDto.setVisualRealImg(rs.getString("visual_real_img"));
				visualDto.setRegDate(rs.getString("regDate"));
				visualList.add(visualDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return visualList;
	}
}



