package com.jjang051.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;

public class LogOutController implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "BoardList.do";
		ModelAndView mav = new ModelAndView(nextPage,"alertMsg","로그아웃 되었습니다.");
		//ModelAndView mav = new ModelAndView(nextPage);
		//mav.addObject("alertMsg", "로그아웃되었습니다.");
		HttpSession session = request.getSession();
		session.invalidate();
		return mav;
	}
}
