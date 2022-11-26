package com.jjang051.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;
import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;

public class BoardViewController implements AbstractController  {
	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "board/view";
		ModelAndView mav = new ModelAndView(nextPage);
		
		int no = Integer.parseInt(request.getParameter("no"));
		int num = Integer.parseInt(request.getParameter("num"));
		ReplyBoardDao replyBoardDao = new ReplyBoardDao();
		
		int result = replyBoardDao.updateHit(no);
		
		ReplyBoardDto replyBoardDto = replyBoardDao.getSelectOne(no);
		mav.addObject("replyBoardDto", replyBoardDto);
		
		
		ReplyBoardDto prevReplyBoardDto = null;
		prevReplyBoardDto = replyBoardDao.getPrevSelect(num);
		mav.addObject("prevReplyBoardDto", prevReplyBoardDto);

		ReplyBoardDto nextReplyBoardDto = null;
		nextReplyBoardDto = replyBoardDao.getNextSelect(num);
		mav.addObject("nextReplyBoardDto", nextReplyBoardDto);
		
		return mav;
	}
}








