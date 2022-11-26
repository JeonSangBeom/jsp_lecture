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
	// 여기서 db에 값을 전달하고 출력하는거 여기에 다있다
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

	//추가할때
	public int insertMember(MemberDto memberDto) {/* insertMember 메서드로 뺴는 것 - 매개변수는 memberDto로(이 매개변수로 값을 넘겨주면 된다) */
		int result = 0;
		getConnection(); //연결하기
		String sql = "INSERT INTO MEMBER VALUES (SEQ_MEMBER.NEXTVAL,?,?,?,?,?,?,?,SYSDATE)";
		try {
			pstmt = conn.prepareStatement(sql); // sql을 먼저 날려주기
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
	
	//전부 불러올때
	// 코드는 같고 하나로 뭉치기 위해 dao 하나로 
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
				System.out.println(memberDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return memberList;
	}
	
	//하나만 가져오기
	public MemberDto getSelectOne(String user_id) { 
		MemberDto memberDto = new MemberDto(); // 값을 가져오고
		getConnection();// 연결 해주고
		String sql = "SELECT * FROM MEMBER WHERE ID = ?"; // sql 지정
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
	
	//로그인시
	public MemberDto getLoggedMember(MemberDto memberDto) { // id와 pw를 넘겨야 하나 memberDto안에 전부 있기 때문에 상관 없다
		MemberDto loggedMember = null; //로그인 할시 처음 값 null설정
		getConnection();
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PASSWORD = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loggedMember = new MemberDto(); // 값이 있으면 dto로 생성
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
	
	//수정
	public int updateMember(MemberDto memberDto) { // result가 int라 memberdto로 받을 수 없다
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
	
	//삭제
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
}





