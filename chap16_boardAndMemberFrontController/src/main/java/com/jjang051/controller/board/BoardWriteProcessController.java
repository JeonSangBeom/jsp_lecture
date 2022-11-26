package com.jjang051.controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;
import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;
import com.jjang051.util.ScriptWriter;



public class BoardWriteProcessController  implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "BoardList.do";
		ModelAndView mav = new ModelAndView(nextPage);
		HttpSession session = request.getSession();
		
		ReplyBoardDto replyBoardDto = new ReplyBoardDto();
		
		replyBoardDto.setSubject(request.getParameter("user_subject"));
		replyBoardDto.setName(request.getParameter("user_name"));
		replyBoardDto.setContents(request.getParameter("user_contents"));
		replyBoardDto.setPassword(request.getParameter("user_pw"));
		replyBoardDto.setEmail(request.getParameter("user_email"));
		replyBoardDto.setId((String)session.getAttribute("loggedId"));
		
		ReplyBoardDao replyBoardDao = new ReplyBoardDao();
		int result = replyBoardDao.insertBoard(replyBoardDto);
		if (result > 0) {
			mav.addObject("alertMsg", "글이 입력되었습니다.");
		} else {
			mav.addObject("backMsg", "알 수 없는 이유로 글이 등록되지 않았습니다. 다시 시도해 주세요");
		}
		return mav;
	}
}



