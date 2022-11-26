package com.jjang051.controller;
//visual.jsp에서 전송시 이동하는 곳

import java.io.File; // io 잊지 않기(파일 만들기)
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.VisualDao;
import com.jjang051.model.VisualDto;
import com.jjang051.util.ScriptWriter;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/VisualUpload.do")
public class VisualUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VisualUploadController() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//멀티파트 만들기
		String savePath = "upload_visual"; // 저장 파일 이름
		ServletContext context = this.getServletContext();// prugio_model2 던져주는 것
		String realPath = context.getRealPath(savePath);// 진짜 링크
		int fileSize = 1024 * 1024 * 50;
		String encoding = "UTF-8";
		DefaultFileRenamePolicy filePolicy = new DefaultFileRenamePolicy();//중복 처리

		File dir = new File(realPath);
		if (!dir.exists())
			dir.mkdir();
		// 업로드 파일 만들어 주기 귀찮으니 만들어주기 디렉토리 자동 생성
		MultipartRequest multipart = new MultipartRequest(request, realPath, fileSize, encoding, filePolicy);
		//데이터 받아주기
		String slogan = multipart.getParameter("user_slogan01");
		String visualImg = multipart.getOriginalFileName("user_visual01");
		String visualRealImg = multipart.getFilesystemName("user_visual01");
		
		System.out.println("slogan===="+slogan);
		System.out.println("slogan===="+visualImg);
		System.out.println("slogan===="+visualRealImg);
		
		VisualDao visualDao = new VisualDao();
		VisualDto visualDto = new VisualDto();
		visualDto.setSlogan(slogan);
		visualDto.setVisualImg(visualImg);
		visualDto.setVisualRealImg(visualRealImg);
		int result = visualDao.insertVisual(visualDto);

		if (result > 0) {
			response.sendRedirect("Index.do");
		} else {
			ScriptWriter.alertAndBack(response, "DB오류");
		}
	}
}