package com.jjang051.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;

public class MemberJoinController implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "member/member_info";
		ModelAndView mav = new ModelAndView("member/join");
		return mav;
	}
}
