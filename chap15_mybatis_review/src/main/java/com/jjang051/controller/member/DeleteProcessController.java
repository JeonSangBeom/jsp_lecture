package com.jjang051.controller.member;

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


@WebServlet("/member/DeleteProcess.do")
public class DeleteProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteProcessController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("user_pw");
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = new MemberDto();
		memberDto.setId(user_id);
		memberDto.setPassword(user_pw);
		int result = memberDao.deleteMember(memberDto);
		HttpSession  session = request.getSession();
		if(result>0) {
			ScriptWriter.alertAndNext(response, "회원탈퇴되었습니다. 이용해주셔서 감사합니다.", "../member/List.do");
			session.invalidate();
		} else {
			ScriptWriter.alertAndBack(response, "아이디 패스워드 확인해주세요.");
		}
	}
}
