package com.jjang051.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DataBaseAccessObject
public class MemberDao {
	// 여기서 db에 값을 전달하고 출력하는거 여기에 다있음....
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
	public int insertMember(MemberDto memberDto) {
		int result = 0;
		getConnection();
		String sql = "INSERT INTO MEMBER VALUES (SEQ_MEMBER.NEXTVAL,?,?,?,?,?,?,?,SYSDATE)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPassword());
			pstmt.setString(3, memberDto.getName());
			pstmt.setString(4, memberDto.getEmail());
			pstmt.setString(5, memberDto.getPhone());
			pstmt.setInt(6, memberDto.getZipCode());
			pstmt.setString(7, memberDto.getAddress());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	public ArrayList<MemberDto> showAllMember() {
		ArrayList<MemberDto> memberList = new ArrayList<>();
		getConnection();
		String sql = "SELECT * FROM MEMBER";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDto memberDto = new MemberDto();
				memberDto.setNo(rs.getInt("no"));
				memberDto.setId(rs.getString("id"));
				memberDto.setPassword(rs.getString("password"));
				memberDto.setName(rs.getString("name"));
				memberDto.setEmail(rs.getString("email"));
				memberDto.setZipCode(rs.getInt("zipCode"));
				memberDto.setPhone(rs.getString("phone"));
				memberDto.setAddress(rs.getString("address"));
				memberDto.setRegDate(rs.getString("regDate"));
				memberList.add(memberDto);
				//System.out.println(memberDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return memberList;
	}
	public MemberDto getSelectOne(String user_id) {
		MemberDto memberDto = new MemberDto();
		getConnection();
		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto.setNo(rs.getInt("no"));
				memberDto.setId(rs.getString("id"));
				memberDto.setPassword(rs.getString("password"));
				memberDto.setName(rs.getString("name"));
				memberDto.setPhone(rs.getString("phone"));
				memberDto.setEmail(rs.getString("email"));
				memberDto.setZipCode(rs.getInt("zipCode"));
				memberDto.setAddress(rs.getString("address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return memberDto;
	}
	public MemberDto getLoggedMember(MemberDto memberDto) {
		MemberDto loggedMember = null;
		getConnection();
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PASSWORD = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loggedMember = new MemberDto();
				loggedMember.setNo(rs.getInt("no"));
				loggedMember.setId(rs.getString("id"));
				loggedMember.setPassword(rs.getString("password"));
				loggedMember.setName(rs.getString("name"));
				loggedMember.setPhone(rs.getString("phone"));
				loggedMember.setEmail(rs.getString("email"));
				loggedMember.setZipCode(rs.getInt("zipCode"));
				loggedMember.setAddress(rs.getString("address"));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return loggedMember;
	}
	public int updateMember(MemberDto memberDto) {
		int result = 0;
		getConnection();
		String sql = "UPDATE MEMBER SET NAME = ?, EMAIL=?, PHONE=?, ZIPCODE=?,ADDRESS=? WHERE ID= ? AND PASSWORD = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getName());
			pstmt.setString(2, memberDto.getEmail());
			pstmt.setString(3, memberDto.getPhone());
			pstmt.setInt(4, memberDto.getZipCode());
			pstmt.setString(5, memberDto.getAddress());
			pstmt.setString(6, memberDto.getId());
			pstmt.setString(7, memberDto.getPassword());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	public int deleteMember(MemberDto memberDto) {
		int result = 0;
		getConnection();
		String sql = "DELETE FROM MEMBER WHERE ID = ? AND PASSWORD = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPassword());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	//ID체크
	public int idCheck(String id) {
		int result = 0;
		try {
			getConnection();
			String sql = "SELECT COUNT(*) AS COUNT FROM MEMBER WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
}
