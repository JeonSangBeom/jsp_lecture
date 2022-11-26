package com.jjang051.controller;

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

@WebServlet("/BoardList.do")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyBoardDao replyBoardDao = new ReplyBoardDao();
		String tempClickPage = request.getParameter("clickPage"); //boardlist에서 넘어온 값
		if(tempClickPage==null) {
			tempClickPage = "1";
		}
		int clickPage = Integer.parseInt(tempClickPage); // 정수로 변환
		
		int totalPage = replyBoardDao.getTotal(); //메서드로 만들어둔 토탈 갯수(전체 갯수)
		
		int listPerPage = 5; // 한번에 한 페이지에 뿌려질 리스트 갯수
		int pageBlock = 3;   // pagination에 뿌려질 페이지 숫자 < 1/2/3 >   < 4/5/6  > 
		int lastPage = 0; // 마지막 페이지 번호
		
		lastPage = (int)(Math.ceil((double)totalPage/listPerPage));
		
	//	if(totalPage%listPerPage==0) {
	//		lastPage = (int)(Math.ceil(totalPage/listPerPage));
	//	} else {
	//		lastPage = (int)(Math.ceil(totalPage/listPerPage))+1;
	//	}
		
		//페이지 리스트 갯수
		//startPage  clickPage 0~3 ===  1 / 3
		//startPage  clickPage 4~6 ===  4 / 6
		//startPage  clickPage 7~9 ===  7 / 9
		int startPage = (int)((clickPage-1) / pageBlock)*pageBlock+1; //0 , 1 , 2(클릭한 숫자대로'1,2,3') 
		int endPage = startPage + pageBlock - 1;
		
		// endPage가 lastPage보다 큰 경우가 생길 수 있다 이때 endPage는 lastPage보다 클 수가 없다.
		// 오류 발생 예방 페이지 갯수가 하나가 늘어나는 오류 발생(내용이 없는데 페이지 리스트가 생기는 것을 방지)
		if(endPage > lastPage) endPage =  lastPage;
		
		//화면에 보이는 갯수
		//clickPage = 1   start = 1   /  end = 5
		//clickPage = 2   start = 6   /  end = 10
		//clickPage = 3   start = 11  /  end = 15
		int start = (clickPage-1)*listPerPage+1; // dao 전달되는 변수
		int end =   start+listPerPage - 1;       // dao 전달되는 변수		
		ArrayList<ReplyBoardDto> boardList = replyBoardDao.getAllList(start,end);
		// 실어두기 - jsp에서 사용하게 하기 위해(넘기기)
		request.setAttribute("boardList", boardList);
		
		//페이지 관련 속성들
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listPerPage", listPerPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("clickPage", clickPage);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("board_list.jsp");
		dispatcher.forward(request, response);
		
		//System.out.print(boardList.);
		
		//System.out.print("1번"+Math.ceil((double)17/5)); 4.0 출력
		//System.out.print("2번"+(int)(Math.ceil((double)17/5))); 4출력
		//System.out.print("3번"+(int)Math.ceil(17/5)); 3출력
		//System.out.print("4번"+Math.ceil((int)17/5)); 3.0출력
	}
}

