package com.jjang051.front;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.controller.AbstractController;
import com.jjang051.util.ScriptWriter;

// .do로 끝나는 모든 url을 받아서 처리하겠다.
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// ModelAndView(spring에 있는 것 - 잊어도 상관은 없다)두가지를 한번에 처리해준다
		// abstract로 설정해둔 것 - controller에서 가져와 사용할 수 있게
		ModelAndView mav = null;
		
		// context - 파일 업로드시에도 사용한적 있음
		// command뽑아내기 위한 ex) / == BoardList.do 를 뽑기위한 명령어들
		String context = request.getContextPath();
		String uri = request.getRequestURI(); // 전체 요청주소....
		String command = uri.substring(context.length());
		// /mybatis/BoardList.do - uri , context는 /mybatis 
		// http://localhost:8090/mybatis/BoardList.do - url 는 전체

		/*
		 * System.out.println("전체주소==="+request.getRequestURL().toString());
		 * System.out.println("http://localhost:8090를 제외한 전체주소==="+uri);
		 * System.out.println("지금 돌아가고 있는 context==="+context);
		 * System.out.println("command==="+command);
		 */
		System.out.println("command==="+command);
		
		
		// view를 mapping해주기위한 접두어 접미어 생성
		ViewResolver viewResolver = new ViewResolver();
		viewResolver.setPrefix("/WEB-INF/"); // 접두어
		viewResolver.setSuffix(".jsp"); // 접미어

		// HandlerMapping에서 controller(값) 얻어오기
		HandlerMapping mappings = new HandlerMapping();
		AbstractController controller = null;
		controller = mappings.getController(command); // command로 던져준 값(키)를 controller(값)에서 가져오는 것 controller 연결(현재 페이지의 .do인)
		
		// ModelAndView - 다음페이지 연결 - 이곳에 controller을 넣어주기(값)
		mav = controller.requestHandler(request, response); // controller에 정의한 값 가져오기
		String tempPage = mav.getNextPage();  //   두가지 케이스가 넘어온다.    board/board_list    or   BoardList.do
		String nextPage = null; //nextPage가 호출 하는 곳으로 tempPage의 값이 불러와 진다
		System.out.println("tempPage===+"+tempPage); // board/board_list 처럼 출력
		
		if(tempPage.contains(".do")) { // .do가 들어가 있으면
			nextPage =  tempPage; //process에서 넘어올 경우 do로 넘어오기 떄문에 이렇게 처리 / 접두어 점미어가 필요 없어짐
		} else {
			nextPage = viewResolver.getViewPage(tempPage); // 위에 설정해둔 값 접두어와 접미어가 들어간다(메서드) /WEB-INF/board/board_list.jsp
		}
		
		
		// 데이터를 싣기. 
		Map<String, Object> model = mav.getModel();
		for (String key : model.keySet()) { // 몇개가 있을지 몰라 반복문으로 사용 
			request.setAttribute(key, model.get(key));
			// System.out.println("key==="+key+":"+model.get(key));
		}
		
		//.do 로 넘어올 떄 다음 페이지에 갈 팝업창 내용을 만들기 위해
		String alertMsg =  (String) model.get("alertMsg");//model에 object를 담았기 떄문에 형변환 필요
		String backMsg =  (String) model.get("backMsg");
		
		System.out.println("alertMsg==="+alertMsg);
		System.out.println("backMsg==="+backMsg);
		
		
		//String nextPage = viewResolver.getViewPage(tempPage);
		// System.out.println("tempPage==="+tempPage);
		// System.out.println("nextPage==="+nextPage);
		if(alertMsg!=null) {
			ScriptWriter.alertAndNext(response, alertMsg, nextPage);
		} else if(backMsg!=null) {
			ScriptWriter.alertAndBack(response, backMsg);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}
}
