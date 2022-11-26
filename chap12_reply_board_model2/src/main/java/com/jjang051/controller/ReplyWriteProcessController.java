package com.jjang051.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;
import com.jjang051.util.ScriptWriter;
//데이터를 받는 것
@WebServlet("/ReplyWriteProcess.do")
public class ReplyWriteProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyWriteProcessController() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		ReplyBoardDto replyBoardDto = new ReplyBoardDto();

		replyBoardDto.setSubject(request.getParameter("user_subject"));
		replyBoardDto.setName(request.getParameter("user_name"));
		replyBoardDto.setContents(request.getParameter("user_contents"));
		replyBoardDto.setPassword(request.getParameter("user_pw"));
		replyBoardDto.setEmail(request.getParameter("user_email"));		
		
		replyBoardDto.setReGroup(  Integer.parseInt( request.getParameter("reGroup") ) );
		replyBoardDto.setRelevel(  Integer.parseInt( request.getParameter("reLevel") ) );
		replyBoardDto.setReStep(  Integer.parseInt( request.getParameter("reStep") ) );
		replyBoardDto.setNo(  Integer.parseInt( request.getParameter("no") ) );
		// reply_write에서 hidden으로 보낸 것을 받아와서 replyBoardDto에 담아두기
		
		

		ReplyBoardDao replyBoardDao = new ReplyBoardDao();
		int result = replyBoardDao.insertReplyBoard(replyBoardDto);// 받아온 데이터 넣어주기 dao에
		if (result > 0) {
			ScriptWriter.alertAndNext(response, "글이 입력되었습니다.", "BoardList.do");
		} else {
			ScriptWriter.alertAndBack(response, "DB오류");
		}
	}
}