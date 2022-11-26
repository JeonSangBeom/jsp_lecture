package com.jjang051.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;
import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;
import com.jjang051.util.PageWriter;

@WebServlet("/board/List.do")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardListController() {
        //super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. jsp에서 넘어오는 데이터들 처리
		// 2. 필요한 메서드 호출
		// 3. request에 데이터 담기
		// 4. forward
		
		request.setCharacterEncoding("UTF-8");
		List<ReplyBoardDto> boardList = null;
		ReplyBoardDao boardDao = new ReplyBoardDao();
		boardList = boardDao.getAllList();
		
		//PageWriter.pageWrite(105,10,5,1,"");
		request.setAttribute("boardList", boardList);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/list.jsp");
		dispatcher.forward(request, response);
	}
}


















