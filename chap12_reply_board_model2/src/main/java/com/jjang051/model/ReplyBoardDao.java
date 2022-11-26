package com.jjang051.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//db왔다갔다 하는 곳
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
	
	
	//새글 쓰기 write .... - 그룹만 증가
	public int insertBoard(ReplyBoardDto replyBoardDto)  {
		int result = 0;
		
		try {
			getConnection();
			conn.setAutoCommit(false);
			int reGroup = 0;
			int reLevel = 1;
			int reStep =  1;
			// 이 세개는 넘어오지 않기 때문에 만들어서 사용해야 한다
			// whrite는 자동으로 위의 세개 값이 들어오게 만듦
			String reGroupSql =  "SELECT MAX(REGROUP) AS REGROUPMAX FROM REPLY_BOARD";
			// 그룹 중 제일 큰걸 불러와서 1증가
			pstmt = conn.prepareStatement(reGroupSql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reGroup = rs.getInt("reGroupMax") + 1;
			}// regroup는 새로 생성이 될때 마다 그룹으로 나뉘기 때문에 
			 // reGroupMax = 컬럼
			
			String sql =  "INSERT INTO REPLY_BOARD VALUES (SEQ_REPLYBOARD.NEXTVAL,?,?,?,?,SYSDATE,?,?,?,0,?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, replyBoardDto.getSubject());
			pstmt.setString(2, replyBoardDto.getName());
			pstmt.setString(3, replyBoardDto.getEmail());
			pstmt.setString(4, replyBoardDto.getPassword());
			pstmt.setInt(5, reGroup);
			pstmt.setInt(6, reStep);
			pstmt.setInt(7, reLevel);
			pstmt.setString(8, replyBoardDto.getContents());
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
	
	// 답글 쓰기 reply write
	public int insertReplyBoard(ReplyBoardDto replyBoardDto) {
		int result = 0;
		
		try {
			getConnection();
			conn.setAutoCommit(false);
			// 자동 저장 안되게 설정
			// 오류 발생 예방  -  오라클과 다르게 자바는 자동으로 commit을 하기 때문에 미리 방지//sql update가 두개를 사용할때 사용해 주어야 한다(트랜잭션(transaction)일 경우)
			int reGroup = replyBoardDto.getReGroup();
			int reLevel = replyBoardDto.getRelevel();
			int reStep =  replyBoardDto.getReStep();
			// 부모 것을 가져오기 (넘어온 값으로 대체 - 처음 새글을 쓸때 만들어 뒀던 값)
			
			
			//같은 그룹에 있는 기존에 있던 LEVEL들 전부 1증가(나보다 큰 level들)		
			String levelAddSql =  "UPDATE REPLY_BOARD SET RELEVEL = RELEVEL + 1 WHERE REGROUP = ? AND RELEVEL > ?";
			pstmt = conn.prepareStatement(levelAddSql);
			pstmt.setInt(1, reGroup);
			pstmt.setInt(2, reLevel);
			pstmt.executeUpdate();
			
			// 이제 추가 되는것 (나)
			String sql =  "INSERT INTO REPLY_BOARD VALUES (SEQ_REPLYBOARD.NEXTVAL,?,?,?,?,SYSDATE,?,?,?,0,?,0)";
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
			result = pstmt.executeUpdate();
			conn.commit();
			// 커밋 써주기 위에 false 쓴것
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				// commit시 오류 발생시 돌려주기
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		close();
		return result;
	}
	
	
	// 전체 리스트 들고오기.... boardlistcontroller에서 쓰는 것
	public ArrayList<ReplyBoardDto> getAllList(int start, int end) {
		ArrayList<ReplyBoardDto> boardList = new ArrayList<>();
		try {
			getConnection();
			//String sql = "SELECT * FROM REPLY_BOARD ORDER BY REGROUP DESC, RELEVEL ASC";
			//위의 쿼리로 사용하면 no의 순서가 시퀀스를 기준으로 정렬이 된다
			//40  39  41
			String sql = "SELECT * FROM "
					+ "(SELECT ROWNUM AS SNUM,B.* FROM "
					+ "    (SELECT * FROM REPLY_BOARD ORDER BY REGROUP DESC, RELEVEL ASC) B"
					+ ") WHERE SNUM >= ? AND SNUM <= ?";
			//ORDER BY REGROUP DESC - 큰숫자를 먼저오게 하기 위해 , RELEVEL ASC - RELEVEL은 반대
			//ROWNUM - 순번매길때 사용
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
				replyBoardDto.setNum(rs.getInt("snum"));
				boardList.add(replyBoardDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return boardList;
	}
	
	// 검색 목록 (boardsearchlist에서 쓸 것)
	public ArrayList<ReplyBoardDto> getSearchAllList(String searchField,String searchWord) {
		 // 매개변수 두개가 넘어올 것이다 board_list.jsp에서
		ArrayList<ReplyBoardDto> boardList = new ArrayList<>();
		try {
			System.out.println("searchField==="+searchField);
			System.out.println("searchWord==="+searchWord);
			getConnection();
			
			// field는 바인딩 안된다.(? 표시가 안된다 +를 붙여서 사용)
			String sql = "SELECT * FROM REPLY_BOARD WHERE "+searchField+" LIKE ?"
					+ "ORDER BY REGROUP DESC, RELEVEL ASC";
			
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, searchField);
			pstmt.setString(1, "%"+searchWord+"%"); // 전부 찾아주게 해주기
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
				//replyBoardDto.setNum(rs.getInt("snum"));
				boardList.add(replyBoardDto);
			}
			System.out.println("size==="+boardList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return boardList;
	}
	
	
	// 게시물 하나 view 들고오기
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
				replyBoardDto.setNum(rs.getInt("snum"));
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
		// 이전글 없음을 사용하기 위해 기존 값을 null로 잡아둬야 한다(값이 없을떄 사용하기 위해)
		try {
			getConnection();
			String sql = "SELECT * FROM "
					+ "(SELECT ROWNUM AS SNUM,B.* FROM "
					+ "    (SELECT * FROM REPLY_BOARD ORDER BY REGROUP DESC, RELEVEL ASC) B"
					+ ") WHERE SNUM = ? - 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				replyBoardDto = new ReplyBoardDto(); // null이 아닐경우 이떄 가져오기
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
				replyBoardDto.setNum(rs.getInt("snum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return replyBoardDto;
	}
	//다음글 가기
	public ReplyBoardDto getNextSelect(int num) {
		ReplyBoardDto replyBoardDto = null;
		
		try {
			getConnection();
			String sql = "SELECT * FROM "
					+ "(SELECT ROWNUM AS SNUM,B.* FROM "
					+ "    (SELECT * FROM REPLY_BOARD ORDER BY REGROUP DESC, RELEVEL ASC) B"
					+ ") WHERE SNUM = ? + 1";
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
				replyBoardDto.setNum(rs.getInt("snum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return replyBoardDto;
	}
	// 메서드 하나 생성 (전체 갯수를 받을 것) - 페이지 갯수를 위해
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
