package com.jjang051.controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;
import com.jjang051.util.ScriptWriter;

@WebServlet("/board/DeleteProcess.do")
public class DeleteProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteProcessController() {
        super();

    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no =  Integer.parseInt( request.getParameter("no") );
		String password = request.getParameter("user_pw");
		ReplyBoardDto replyBoardDto = new ReplyBoardDto();
		ReplyBoardDao replyBoardDao = new ReplyBoardDao();
		replyBoardDto.setNo(no);
		replyBoardDto.setPassword(password);
		int result = replyBoardDao.deleteBoard(replyBoardDto);
		if(result > 0 ) {
			ScriptWriter.alertAndNext(response, "글이 삭제되었습니다.", "../board/Index.do");
		} else {
			ScriptWriter.alertAndBack(response, "DB오류입니다.");
		}
	}
}
