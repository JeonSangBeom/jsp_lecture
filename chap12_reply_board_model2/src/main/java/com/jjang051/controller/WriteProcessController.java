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


@WebServlet("/WriteProcess.do")
public class WriteProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WriteProcessController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");		
		
		ReplyBoardDto replyBoardDto = new ReplyBoardDto();
		
		replyBoardDto.setSubject(request.getParameter("user_subject")); // write.jsp에서 지정한name
		replyBoardDto.setName(request.getParameter("user_name"));
		replyBoardDto.setContents(request.getParameter("user_contents"));
		replyBoardDto.setPassword(request.getParameter("user_pw"));
		replyBoardDto.setEmail(request.getParameter("user_email"));
								
		ReplyBoardDao replyBoardDao = new ReplyBoardDao();		
		int result = replyBoardDao.insertBoard(replyBoardDto);
		if(result>0) {
			ScriptWriter.alertAndNext(response, "글이 입력되었습니다.", "BoardList.do");
		} else {
			ScriptWriter.alertAndBack(response, "DB오류");
		}
	}
}

