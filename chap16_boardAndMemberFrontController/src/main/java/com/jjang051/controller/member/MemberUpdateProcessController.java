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

public class MemberUpdateProcessController implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "BoardList.do";
		ModelAndView mav = new ModelAndView(nextPage);
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		String user_phone = "";
		String user_phone_first = request.getParameter("user_phone_first");
		String user_phone_middle = request.getParameter("user_phone_middle");
		String user_phone_last = request.getParameter("user_phone_last");
		user_phone = user_phone_first+"-"+user_phone_middle+"-"+user_phone_last;
		int user_zipcode = Integer.parseInt( request.getParameter("zipcode") );
		String user_address = "";
		String address01 = request.getParameter("address01");
		String address02 = request.getParameter("address02");
		user_address = address01+address02;

		MemberDto memberDto = new MemberDto();
		MemberDao memberDao = new MemberDao();
		memberDto.setId(user_id);
		memberDto.setName(user_name);
		memberDto.setZipCode(user_zipcode);
		memberDto.setAddress(user_address);
		memberDto.setPassword(user_pw);
		memberDto.setPhone(user_phone);
		memberDto.setEmail(user_email);
		
		int result = memberDao.updateMember(memberDto);
		HttpSession session = request.getSession();
		if(result > 0) {
			session.setAttribute("loggedName", user_name);
			mav.addObject("alertMsg", "??????????????? ?????????????????????.");
		} else {
			mav.addObject("backMsg", "??????????????? ????????? ?????????.");
		}
		return mav;
	}
}
