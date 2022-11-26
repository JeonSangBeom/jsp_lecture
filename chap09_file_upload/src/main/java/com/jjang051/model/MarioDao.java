package com.jjang051.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarioDao {
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
	public int insertMario(MarioDto marioDto) {
		int result = 0;
		try {
			getConnection();// 연결
			String sql =  "INSERT INTO MARIO VALUES (SEQ_MARIO.NEXTVAL,?,?,?,?,?,?)";// 집어 넣는 insert
			pstmt = conn.prepareStatement(sql);// 먼저 보내놓고 아래 받아오기
			pstmt.setString(1, marioDto.getTitle());
			pstmt.setString(2, marioDto.getContents());
			pstmt.setString(3, marioDto.getLink());
			pstmt.setString(4, marioDto.getBg());
			pstmt.setString(5, marioDto.getMarioImg());
			pstmt.setString(6, marioDto.getMarioRealImg());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	public ArrayList<MarioDto> getAllList() {// ArrayList 임폴트
		ArrayList<MarioDto> marioList = new ArrayList<>(); //  ArrayList<MarioDto>를 받는 반환
		try {
			getConnection();
			String sql = "SELECT * FROM MARIO";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MarioDto marioDto = new MarioDto();
				marioDto.setNo(rs.getInt("no"));
				marioDto.setTitle(rs.getString("title"));
				marioDto.setLink(rs.getString("link"));
				marioDto.setBg(rs.getString("bg"));
				marioDto.setContents(rs.getString("contents"));
				marioDto.setMarioImg(rs.getString("mario_img"));//mario_img 컬럼명 확인(대소문자는 구분 없음)
				marioDto.setMarioRealImg(rs.getString("mario_real_img"));
				marioList.add(marioDto);// marioList에다가 실어서 넘겨주기
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return marioList;
	}
}

