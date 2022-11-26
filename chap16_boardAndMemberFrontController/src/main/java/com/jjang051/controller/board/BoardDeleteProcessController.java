package com.jjang051.controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;
import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;
import com.jjang051.util.ScriptWriter;


public class BoardDeleteProcessController implements AbstractController{

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "BoardList.do";
		ModelAndView mav = new ModelAndView(nextPage);
		int no =  Integer.parseInt( request.getParameter("no") );
		String password = request.getParameter("user_pw");
		ReplyBoardDto replyBoardDto = new ReplyBoardDto();
		ReplyBoardDao replyBoardDao = new ReplyBoardDao();
		replyBoardDto.setNo(no);
		replyBoardDto.setPassword(password);
		System.out.println("no=="+no+"===password=="+password);
		
		
		int result = replyBoardDao.deleteBoard(replyBoardDto);
		if(result > 0 ) {
			mav.addObject("alertMsg", "글이 삭제되었습니다.");
		} else {
			mav.addObject("backMsg", "패스워드를 확인해 주세요.");
		}
		return mav;
	}
}
