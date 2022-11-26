package com.jjang051.controllrt.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;
import com.jjang051.util.ScriptWriter;

@WebServlet("/member/LoginProcess.do")
public class LoginProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginProcessController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		MemberDto memberDto = new MemberDto();
		memberDto.setId(user_id);
		memberDto.setPassword(user_pw);
		MemberDao memberDao = new MemberDao();
		MemberDto loggedMember = memberDao.getLoggedMember(memberDto);
		HttpSession session = request.getSession(); 
		// jsp에서는 내장 객체로 존재하고 servlet에서는 없음.... 선언하고 써야 함...
		if(loggedMember!=null) {
			session.setAttribute("loggedId", loggedMember.getId());
			session.setAttribute("loggedName", loggedMember.getName());
			session.setAttribute("loggedMember", loggedMember);
			ScriptWriter.alertAndNext(response, loggedMember.getName()+"님 안녕하세요.", "../board/Index.do");
		} else {
			ScriptWriter.alertAndBack(response, "아이디 비밀번호 확인해 주세요.");
		}
	}
}
