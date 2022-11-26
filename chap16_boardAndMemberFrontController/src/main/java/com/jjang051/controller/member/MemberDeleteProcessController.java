package com.jjang051.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;
import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;
import com.jjang051.util.ScriptWriter;

public class MemberDeleteProcessController implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "BoardList.do";
		ModelAndView mav = new ModelAndView(nextPage);
		String user_pw = request.getParameter("user_pw");
		MemberDto memberDto = new MemberDto();
		MemberDao memberDao = new MemberDao();
		HttpSession session = request.getSession();
		memberDto.setId((String) session.getAttribute("loggedId"));
		memberDto.setPassword(user_pw);
		int result = memberDao.deleteMember(memberDto);
		if (result > 0) {
			mav.addObject("alertMsg", "회원탈되 되었습니다.");
			session.invalidate();
		} else {
			mav.addObject("backMsg", "아이디 패스워드 확인 해주세요");
		}
		return mav;
	}

}
