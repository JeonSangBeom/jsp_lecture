package com.jjang051.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.ClockDao;
import com.jjang051.model.ClockDto;
import com.jjang051.util.ScriptWriter;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//model2의 핵심 - dispatcher은 서버를 변경해주는 역할(jsp파일을 이제 노출 시키지 않을 예정)
// jsp에 썼던 파일들을 do에 써서 넘겨버리게 하겠다는 의미
// servlet은 자바에다 html을 쓰는 것
// jsp는 html에 자바를 쓰는 것    
// servlet 에서 service체크하여 post get 끄고 만들기
@WebServlet("/ClockUploadController.do")// (경로를 임의로 쓴 것)여기에 접근을 하게 되면 결과가 나오는 것
public class ClockUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ClockUploadController() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  service(선택할떄 고르른 것)는 get / post  (둘다 포함 즉 action에서 넘어오는거(post/get) 둘다 받겠다.)
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 데이터 받는 것 은 동일
		
		//파일업로드 등록시
		String savePath = "upload_clock";
		ServletContext context = this.getServletContext();// this = application = request -> 전부 clock폴더를 가리키는 것이다
		String realPath = context.getRealPath(savePath);// 진짜 경로
		
		int fileSize = 1024*1024*50;
		String encoding= "UTF-8";
		
		File dir = new File(realPath);
		if(!dir.exists()) { // 존재하지 않는 다면
			dir.mkdir(); // 만들어줘라
		}
		/* 일일이 폴더 안으로 찾아가 realPath가 가리키는 폴더명을 만들필요 없이 알아서 만들어 준다
		C:\Users\jsb43\Desktop\jsp-lecture\.metadata\plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jsp-clock\ㅇㅇㅇ_clock*/	
		
		DefaultFileRenamePolicy fileRenamePolicy = new DefaultFileRenamePolicy();
		//UUID(직접 뽑아 만들때 사용), 날짜 뽑아서 하는 경우도 있음 / cos에서 제공된 라이브러리 - 중복 처리 파일		
		
		MultipartRequest multipart = new MultipartRequest(request, realPath,fileSize, encoding,fileRenamePolicy);
		// 파일 업로드시 필요(request, 파일저장경로, 업로드 파일 사이즈 , 인코딩, 중복파일 처리)
		
		
		
		// 넘어온 값을 한번에 안받고 따로 쓸 경우 ---
		// String category = multipart.getParameter("category");
		// request로 받아올 경우 null값으로 떨어진다
		// multipart로 받아와야 값을 정상적으로 받아온다
		
		ClockDao clockDao = new ClockDao(); // DB왔다갔다하는 것  
		ClockDto clockDto = new ClockDto();
		
		clockDto.setCategory(multipart.getParameter("category"));
		clockDto.setTitle(multipart.getParameter("title"));
		clockDto.setDepth(Integer.parseInt(multipart.getParameter("depth")));
		clockDto.setPrice(Integer.parseInt(multipart.getParameter("price")));
		clockDto.setLink(multipart.getParameter("link"));
		clockDto.setClockImg(multipart.getOriginalFileName("hublotFile"));
		clockDto.setClockRealImg(multipart.getFilesystemName("hublotFile"));
		int result = clockDao.insertClock(clockDto);
		if(result>0) {
			response.sendRedirect("Main.do"); // 대답.대답을 돌려줘라("Main.do")
		} else {
			ScriptWriter.alertAndBack(response, "DB오류입니다.");
		}
	}
}







