package com.jjang051.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;
import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;
import com.jjang051.util.ScriptWriter;

public class LoginProcessController implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "BoardList.do";
		ModelAndView mav = null;
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		MemberDto memberDto = new MemberDto();
		MemberDao memberDao = new MemberDao();
		memberDto.setId(user_id);
		memberDto.setPassword(user_pw);
		//데이터 싣고 nextpage결정해주면 front에서 다 해준다.
		
		MemberDto loggedMember = memberDao.getLoggedMember(memberDto);
		if(loggedMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedId", loggedMember.getId());
			session.setAttribute("loggedName", loggedMember.getName());
			session.setAttribute("loggedMember", loggedMember);
			mav = new ModelAndView(nextPage,"alertMsg",loggedMember.getName()+"님 로그인 되었습니다."); // 키와 값 실어서 넣기
		} else {
			mav = new ModelAndView(nextPage,"backMsg","아이디 패스워드 확인해 주세요.");
		}
		return mav;
	}
}
