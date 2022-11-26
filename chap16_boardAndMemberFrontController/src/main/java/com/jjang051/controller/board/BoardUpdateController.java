package com.jjang051.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;

public class BoardUpdateController implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "board/update";
		ModelAndView mav = new ModelAndView(nextPage);
		return mav;
	}

}
