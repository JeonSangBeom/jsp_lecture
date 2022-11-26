package com.jjang051.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.BoardDao;
import com.jjang051.model.BoardDto;
import com.jjang051.util.ScriptWriter;

@WebServlet("/DeleteProcess.do")
public class DeleteProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteProcessController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao boardDao = new BoardDao();
		BoardDto boardDto = new BoardDto();
		boardDto.setNo( Integer.parseInt( request.getParameter("no")));// list 에서 쿼리스트링으로 넘겨준 값 -> view
		boardDto.setPassword(request.getParameter("user_pw"));// delete에서 name
		int result = boardDao.deleteBoard(boardDto);
		if(result > 0) {
			ScriptWriter.alertAndNext(response, "삭제하였습니다", "List.do");
		} else {
			ScriptWriter.alertAndBack(response, "비밀번호를 확인하여 주세요");
		}
	}

}
