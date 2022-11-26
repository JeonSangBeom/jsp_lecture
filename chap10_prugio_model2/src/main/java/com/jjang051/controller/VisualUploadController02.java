package com.jjang051.controller;

import java.io.File;
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

@WebServlet("/VisualUpload02.do")
public class VisualUploadController02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VisualUploadController02() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String savePath = "upload_visual";
		ServletContext context = this.getServletContext();
		String realPath = context.getRealPath(savePath);
		int fileSize = 1024 * 1024 * 50;
		String encoding = "UTF-8";
		DefaultFileRenamePolicy filePolicy = new DefaultFileRenamePolicy();

		File dir = new File(realPath);
		if (!dir.exists())
			dir.mkdir();
		MultipartRequest multipart = new MultipartRequest(request, realPath, fileSize, encoding, filePolicy);
		// 여기까진 같다 하나 받든 여러개 받든 같다 아래부터 달라진다
		
		//Iterator와 유사하다
		Enumeration files =  multipart.getFileNames(); //  Enumeration (util에 있는 것 - arraylist와 비슷핟다)여러개의 파일을 받을 수 있다...
		int count = 0;
		
		while(files.hasMoreElements()) { // 다음 요소가 있으면 반복문을 돌리기 / hasnext가 아닌 hasMoreElements 사용
			count++;
			
			String visual = (String)files.nextElement();// 반복돌릴경우 파일을 바로 받아 쓸수 없고 이런식으로 받아 사용하여야 한다
			
			String slogan = multipart.getParameter("user_slogan"+count); // 넘어오는것도 count로 숫자를 추가하였기 때문에 이런식으로 적용시킨다
			String visualImg = multipart.getOriginalFileName(visual);
			String visualRealImg = multipart.getFilesystemName(visual);
			VisualDao visualDao = new VisualDao();
			VisualDto visualDto = new VisualDto();
			visualDto.setSlogan(slogan);
			visualDto.setVisualImg(visualImg);
			visualDto.setVisualRealImg(visualRealImg);
			int result = visualDao.insertVisual(visualDto);
		}
//		if (result > 0) {
//			response.sendRedirect("Index.do");
//		} else {
//			ScriptWriter.alertAndBack(response, "DB오류");
//		}
	}
}