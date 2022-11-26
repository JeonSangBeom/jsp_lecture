package com.jjang051.controllrt.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;
import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;


@WebServlet("/member/MemberList.do")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberListController() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberDao memberDao = new MemberDao();
		
		String tempClickPage = request.getParameter("clickPage");
		String search_select = request.getParameter("search_select");
		String search_word = request.getParameter("search_word");
		if(tempClickPage==null) {
			tempClickPage = "1";
		}
		int clickPage = Integer.parseInt(tempClickPage);
		
		int totalPage = memberDao.getTotal(search_select,search_word); //db에 저장되어 있는 페이지 갯수
		
		int listPerPage = 5; // 한번에 뿌려질 페이지리스트 갯수
		int pageBlock =   3;   // pagination에 뿌려질 페이지 숫자 < 1/2/3 >   < 4/5/6  >
		int lastPage = 0; // 마지막 페이지 번호
		if(totalPage%listPerPage==0) {
			lastPage = (int)(Math.ceil(totalPage/listPerPage));
		} else {
			lastPage = (int)(Math.ceil(totalPage/listPerPage))+1;
		}
		
		int startPage = (int)((clickPage-1) / pageBlock)*pageBlock+1;  
		int endPage = startPage + pageBlock - 1;
		
		// endPage가 lastPage보다 큰 경우가 생길 수 있다 이때 endPage는 lastPage보다 클 수가 없다.
		if(endPage > lastPage) endPage =  lastPage;
		
		
		int start = (clickPage-1)*listPerPage+1; // dao 전달되는 변수
		int end =   start+listPerPage - 1;       // dao 전달되는 변수
		
		List<MemberDto> memberList = memberDao.getAllMemberList(start,end,search_select,search_word);
		//페이지 관련 속성들
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listPerPage", listPerPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("clickPage", clickPage);
		
		request.setAttribute("memberList", memberList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/list.jsp");
		dispatcher.forward(request, response);
	}
}













