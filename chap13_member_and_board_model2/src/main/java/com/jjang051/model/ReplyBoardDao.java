package com.jjang051.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReplyBoardDao {
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
	//새글 쓰기....(1 / 2)
	public int insertBoard(ReplyBoardDto replyBoardDto)  {
		int result = 0;
		
		try {
			getConnection();
			conn.setAutoCommit(false);
			int reGroup = 0;
			int reLevel = 1;
			int reStep =  1;
			String reGroupSql =  "SELECT MAX(REGROUP) AS REGROUPMAX FROM REPLY_BOARD";
			pstmt = conn.prepareStatement(reGroupSql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reGroup = rs.getInt("reGroupMax") + 1;
			}
			String sql =  "INSERT INTO REPLY_BOARD VALUES (SEQ_REPLYBOARD.NEXTVAL,?,?,?,?,SYSDATE,?,?,?,0,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, replyBoardDto.getSubject());
			pstmt.setString(2, replyBoardDto.getName());
			pstmt.setString(3, replyBoardDto.getEmail());
			pstmt.setString(4, replyBoardDto.getPassword());
			pstmt.setInt(5, reGroup);
			pstmt.setInt(6, reStep);
			pstmt.setInt(7, reLevel);
			pstmt.setString(8, replyBoardDto.getContents());
			pstmt.setString(9, replyBoardDto.getId());
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		close();
		return result;
	}
	
	// 답글 쓰기
	public int insertReplyBoard(ReplyBoardDto replyBoardDto) {
		int result = 0;
		
		try {
			getConnection();
			conn.setAutoCommit(false);
			int reGroup = replyBoardDto.getReGroup();
			int reLevel = replyBoardDto.getRelevel();
			int reStep =  replyBoardDto.getReStep();
			
			
			//같은 그룹에 있는 기존에 있던 LEVEL들 전부 1증가....
			String levelAddSql =  "UPDATE REPLY_BOARD SET RELEVEL = RELEVEL + 1 WHERE REGROUP = ? AND RELEVEL > ?";
			pstmt = conn.prepareStatement(levelAddSql);
			pstmt.setInt(1, reGroup);
			pstmt.setInt(2, reLevel);
			pstmt.executeUpdate();
			
			
			String sql =  "INSERT INTO REPLY_BOARD VALUES (SEQ_REPLYBOARD.NEXTVAL,?,?,?,?,SYSDATE,?,?,?,0,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, replyBoardDto.getSubject());
			pstmt.setString(2, replyBoardDto.getName());
			pstmt.setString(3, replyBoardDto.getEmail());
			pstmt.setString(4, replyBoardDto.getPassword());
			// step과 level각각 증가...
			pstmt.setInt(5, reGroup);
			pstmt.setInt(6, reStep + 1);
			pstmt.setInt(7, reLevel + 1);
			pstmt.setString(8, replyBoardDto.getContents());
			pstmt.setString(9, replyBoardDto.getId());
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		close();
		return result;
	}
	
	
	// 전체 리스트 들고오기....
	public ArrayList<ReplyBoardDto> getAllList(int start, int end) {
		ArrayList<ReplyBoardDto> boardList = new ArrayList<>();
		try {
			getConnection();
			//String sql = "SELECT * FROM REPLY_BOARD ORDER BY REGROUP DESC, RELEVEL ASC";
			//40  39  41
			String sql = "SELECT * FROM "
					+ "(SELECT ROWNUM AS NUM,B.* FROM "
					+ "    (SELECT * FROM REPLY_BOARD ORDER BY REGROUP DESC, RELEVEL ASC) B"
					+ ") WHERE NUM >= ? AND NUM <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplyBoardDto replyBoardDto = new ReplyBoardDto();
				replyBoardDto.setNo(rs.getInt("no"));
				replyBoardDto.setSubject(rs.getString("subject"));
				replyBoardDto.setName(rs.getString("name"));
				replyBoardDto.setEmail(rs.getString("email"));
				replyBoardDto.setPassword(rs.getString("password"));
				replyBoardDto.setContents(rs.getString("contents"));
				replyBoardDto.setRegDate(rs.getString("regDate"));
				replyBoardDto.setReGroup(rs.getInt("reGroup"));
				replyBoardDto.setReStep(rs.getInt("reStep"));
				replyBoardDto.setRelevel(rs.getInt("reLevel"));
				replyBoardDto.setHit(rs.getInt("hit"));
				replyBoardDto.setNum(rs.getInt("num"));
				replyBoardDto.setId(rs.getString("id"));
				boardList.add(replyBoardDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return boardList;
	}
	
	// 검색 목록 
	public ArrayList<ReplyBoardDto> getSearchAllList(String searchField,String searchWord) {
		ArrayList<ReplyBoardDto> boardList = new ArrayList<>();
		try {
			System.out.println("searchField==="+searchField);
			System.out.println("searchWord==="+searchWord);
			getConnection();
			// field는 바인딩 안됨...
			String sql = "SELECT * FROM REPLY_BOARD WHERE "+searchField+" LIKE ?"
					+ "ORDER BY REGROUP DESC, RELEVEL ASC";
			
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, searchField);
			pstmt.setString(1, "%"+searchWord+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplyBoardDto replyBoardDto = new ReplyBoardDto();
				replyBoardDto.setNo(rs.getInt("no"));
				replyBoardDto.setSubject(rs.getString("subject"));
				replyBoardDto.setName(rs.getString("name"));
				replyBoardDto.setEmail(rs.getString("email"));
				replyBoardDto.setPassword(rs.getString("password"));
				replyBoardDto.setContents(rs.getString("contents"));
				replyBoardDto.setRegDate(rs.getString("regDate"));
				replyBoardDto.setReGroup(rs.getInt("reGroup"));
				replyBoardDto.setReStep(rs.getInt("reStep"));
				replyBoardDto.setRelevel(rs.getInt("reLevel"));
				replyBoardDto.setHit(rs.getInt("hit"));
				replyBoardDto.setNum(rs.getInt("num"));
				replyBoardDto.setId(rs.getString("id"));
				boardList.add(replyBoardDto);
			}
			System.out.println("size==="+boardList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return boardList;
	}
	
	// 게시물 하나 들고오기
	public ReplyBoardDto getSelectOne(int no) {
		ReplyBoardDto replyBoardDto = new ReplyBoardDto();
		
		try {
			getConnection();
			
			// hit 증가
			String addHitSql = "UPDATE REPLY_BOARD SET HIT = HIT + 1 WHERE NO = ?";
			pstmt = conn.prepareStatement(addHitSql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			
			// 다 뽑아오기...
			String sql = "SELECT * FROM REPLY_BOARD WHERE NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				replyBoardDto.setNo(rs.getInt("no"));
				replyBoardDto.setSubject(rs.getString("subject"));
				replyBoardDto.setName(rs.getString("name"));
				replyBoardDto.setEmail(rs.getString("email"));
				replyBoardDto.setPassword(rs.getString("password"));
				replyBoardDto.setContents(rs.getString("contents"));
				replyBoardDto.setRegDate(rs.getString("regDate"));
				replyBoardDto.setReGroup(rs.getInt("reGroup"));
				replyBoardDto.setReStep(rs.getInt("reStep"));
				replyBoardDto.setRelevel(rs.getInt("reLevel"));
				replyBoardDto.setHit(rs.getInt("hit"));
				replyBoardDto.setId(rs.getString("id"));
				//replyBoardDto.setNum(rs.getInt("num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replyBoardDto;
	}
	
	//삭제
	public int deleteBoard(ReplyBoardDto boardDto) {
		int result = 0;
		
		try {
			getConnection();
			String sql = "DELETE FROM REPLY_BOARD WHERE NO = ?  AND PASSWORD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardDto.getNo());
			pstmt.setString(2, boardDto.getPassword());
			result= pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	// 이전글 가져오기...
	public ReplyBoardDto getPrevSelect(int num) {
		ReplyBoardDto replyBoardDto = null;
		
		try {
			getConnection();
			String sql = "SELECT * FROM "
					+ "(SELECT ROWNUM AS NUM,B.* FROM "
					+ "    (SELECT * FROM REPLY_BOARD ORDER BY REGROUP DESC, RELEVEL ASC) B"
					+ ") WHERE NUM = ? - 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				replyBoardDto = new ReplyBoardDto();
				replyBoardDto.setNo(rs.getInt("no"));
				replyBoardDto.setSubject(rs.getString("subject"));
				replyBoardDto.setName(rs.getString("name"));
				replyBoardDto.setEmail(rs.getString("email"));
				replyBoardDto.setPassword(rs.getString("password"));
				replyBoardDto.setContents(rs.getString("contents"));
				replyBoardDto.setRegDate(rs.getString("regDate"));
				replyBoardDto.setReGroup(rs.getInt("reGroup"));
				replyBoardDto.setReStep(rs.getInt("reStep"));
				replyBoardDto.setRelevel(rs.getInt("reLevel"));
				replyBoardDto.setHit(rs.getInt("hit"));
				replyBoardDto.setNum(rs.getInt("num"));
				replyBoardDto.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return replyBoardDto;
	}
	public ReplyBoardDto getNextSelect(int num) {
		ReplyBoardDto replyBoardDto = null;
		
		try {
			getConnection();
			String sql = "SELECT * FROM "
					+ "(SELECT ROWNUM AS NUM,B.* FROM "
					+ "    (SELECT * FROM REPLY_BOARD ORDER BY REGROUP DESC, RELEVEL ASC) B"
					+ ") WHERE NUM = ? + 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				replyBoardDto = new ReplyBoardDto();
				replyBoardDto.setNo(rs.getInt("no"));
				replyBoardDto.setSubject(rs.getString("subject"));
				replyBoardDto.setName(rs.getString("name"));
				replyBoardDto.setEmail(rs.getString("email"));
				replyBoardDto.setPassword(rs.getString("password"));
				replyBoardDto.setContents(rs.getString("contents"));
				replyBoardDto.setRegDate(rs.getString("regDate"));
				replyBoardDto.setReGroup(rs.getInt("reGroup"));
				replyBoardDto.setReStep(rs.getInt("reStep"));
				replyBoardDto.setRelevel(rs.getInt("reLevel"));
				replyBoardDto.setHit(rs.getInt("hit"));
				replyBoardDto.setNum(rs.getInt("num"));
				replyBoardDto.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return replyBoardDto;
	}
	public int getTotal() {
		int total = 0;
		try {
			getConnection();
			String sql = "SELECT COUNT(*) AS TOTAL FROM REPLY_BOARD";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
}







