package com.jjang051.controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;
import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;

public class IDCheckController implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "member/id_check";
		ModelAndView mav = new ModelAndView(nextPage);
		String user_id = request.getParameter("user_id");
		MemberDao memberDao = new MemberDao();
		int result = memberDao.idCheck(user_id);
		Map<String, Integer> resultMap = new HashMap<>();
		resultMap.put("count", result); // {count:0}
		Gson gson = new Gson();
		String json = (String) gson.toJson(resultMap);
		mav.addObject("json", json);
		return mav;
	}
}
