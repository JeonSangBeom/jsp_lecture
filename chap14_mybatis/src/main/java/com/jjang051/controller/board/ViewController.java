package com.jjang051.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;

@WebServlet("/board/View.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewController() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		int num = Integer.parseInt(request.getParameter("num"));
		ReplyBoardDao replyBoardDao = new ReplyBoardDao();
		
		//증가 시켜줄 hit 하나다 성생
		int result = replyBoardDao.updateHit(no);
		
		ReplyBoardDto replyBoardDto = replyBoardDao.getSelectOne(no);
		request.setAttribute("replyBoardDto", replyBoardDto);

		// 이전글 보기
		// no는 SEQUENCE이기 때문에 다음글과 연결이 되지 않는다. ROWNUM을 이용해서 다음글 이전 글 가져오기가 가능하다.
		ReplyBoardDto prevReplyBoardDto = null;
		prevReplyBoardDto = replyBoardDao.getPrevSelect(num);
		request.setAttribute("prevReplyBoardDto", prevReplyBoardDto);

		ReplyBoardDto nextReplyBoardDto = null;
		nextReplyBoardDto = replyBoardDao.getNextSelect(num);
		request.setAttribute("nextReplyBoardDto", nextReplyBoardDto);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/view.jsp");
		dispatcher.forward(request, response);
	}
}








