package com.jjang051.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;

@WebServlet("/board/BoardList.do")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyBoardDao replyBoardDao = new ReplyBoardDao();
		String tempClickPage = request.getParameter("clickPage");
		if(tempClickPage==null) {
			tempClickPage = "1";
		}
		int clickPage = Integer.parseInt(tempClickPage);
		
		int totalPage = replyBoardDao.getTotal(); //db에 저장되어 있는 페이지 갯수
		
		int listPerPage = 5; // 한번에 뿌려질 페이지리스트 갯수
		int pageBlock =   3;   // pagination에 뿌려질 페이지 숫자 < 1/2/3 >   < 4/5/6  >
		int lastPage = 0; // 마지막 페이지 번호
		if(totalPage%listPerPage==0) {
			lastPage = (int)(Math.ceil(totalPage/listPerPage));
		} else {
			lastPage = (int)(Math.ceil(totalPage/listPerPage))+1;
		}
	
		//startPage  clickPage 0~3 ===  1
		//startPage  clickPage 4~6 ===  4
		//startPage  clickPage 7~9 ===  7
		
		int startPage = (int)((clickPage-1) / pageBlock)*pageBlock+1;  
		int endPage = startPage + pageBlock - 1;
		
		// endPage가 lastPage보다 큰 경우가 생길 수 있다 이때 endPage는 lastPage보다 클 수가 없다.
		if(endPage > lastPage) endPage =  lastPage;
		
		//clickPage = 1   start = 1   /  end = 5
		//clickPage = 2   start = 6   /  end = 10
		//clickPage = 3   start = 11  /  end = 15
		
		int start = (clickPage-1)*listPerPage+1; // dao 전달되는 변수
		int end =   start+listPerPage - 1;       // dao 전달되는 변수
		
		ArrayList<ReplyBoardDto> boardList = replyBoardDao.getAllList(start,end);
		request.setAttribute("boardList", boardList);
		//페이지 관련 속성들
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listPerPage", listPerPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("clickPage", clickPage);
		
		//WEB-INF 경로는 브라우저에서 접근 불가능....
		//Spring은 WEb-INF밑에 views라는 폴더를 만들어 놓고 쓴다.
		//view Resolver를 통해서 prefix랑 suffix를 통해 파일의 경로는 만들어 쓴다.
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/board/board_list.jsp");
		dispatcher.forward(request, response);
	}
}

//




