package com.jjang051.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;
import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;
import com.jjang051.util.ScriptWriter;


public class MemberJoinProcessController implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nextPage = "BoardList.do";
		ModelAndView mav = new ModelAndView(nextPage);
		
		MemberDto memberDto =  new MemberDto();
		MemberDao memberDao = new MemberDao();
		
		String user_phone_first = request.getParameter("user_phone_first");
		String user_phone_middle = request.getParameter("user_phone_middle");
		String user_phone_last = request.getParameter("user_phone_last");
		String user_phone = user_phone_first+"-"+user_phone_middle+"-"+user_phone_last;

		String user_address = "";
		String address01 = request.getParameter("address01");
		String address02 = request.getParameter("address02");
		user_address = address01+address02;
		
		memberDto.setId(request.getParameter("user_id"));  
		memberDto.setName(request.getParameter("user_name"));  
		memberDto.setPassword(request.getParameter("user_pw"));  
		memberDto.setEmail(request.getParameter("user_email"));  
		memberDto.setPhone(user_phone);
		memberDto.setZipCode(Integer.parseInt( request.getParameter("zipcode") ));
		memberDto.setAddress(user_address);
		
		
		int result = memberDao.insertMember(memberDto);
		if(result>0) {
			mav.addObject("alertMsg", "회원가입이 되었습니다.");
		} else {
			mav.addObject("backMsg", "회원가입에 실퍃였습니다.");
		}
		return mav;
	}
}
