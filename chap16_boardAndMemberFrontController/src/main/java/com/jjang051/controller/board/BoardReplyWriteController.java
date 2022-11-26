package com.jjang051.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;

public class BoardReplyWriteController implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "board/reply_write";
		ModelAndView mav = new ModelAndView(nextPage);
		mav.addObject("reGroup",request.getParameter("reGroup"));
		mav.addObject("reStep",request.getParameter("reStep"));
		mav.addObject("reLevel",request.getParameter("reLevel"));
		mav.addObject("no",request.getParameter("no"));
		return mav;
	}
}
