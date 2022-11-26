package com.jjang051.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/SummerNoteFileUpload.do")
public class SummerNoteFileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L; 

    public SummerNoteFileUploadController() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String savePath = "summernote_upload";
		ServletContext context = this.getServletContext();//현재 폴더 경로
		String realPath = context.getRealPath(savePath);//실제 들어가는 경로(일반적인 표시 - 절대 경로)
		
		int fileSize = 1024*1024*100;
		String encoding = "UTF-8";
		DefaultFileRenamePolicy filePolicy = new DefaultFileRenamePolicy();
		
		File dir = new File(realPath);// java.io에 있는 파일 import
		if(!dir.exists()) dir.mkdir();
		
		MultipartRequest multipart =  new MultipartRequest(request, realPath, fileSize, encoding,filePolicy);
		//MultipartRequest는 데이터를 넘길때 get으로 못 넘기고 post로만 넘길 수 있다(get은 2에 8승까지밖에 사용 불가)
		
		/* 받는 두가지 방식
		  Enumeration files = multipart.getFileNames(); 
		  String file = (String)files.nextElement();
		  String fileRealName = multipart.getFilesystemName(file); // 같은 이름 중복시 바뀌어서 들어가게 만들기
		 */		
		String fileRealName = multipart.getFilesystemName("uploadFile");
		// 둘중 하나 사용 가능 uploadFile은 write에 거론한 것(ajax)
		
		String uploadPath = savePath+"/"+fileRealName;
		//파일에서 바라보는 write.jsp에서 바라보는 경로...   summernote_upload/visual_011.jpg -> uploadPath 이걸 아래 url에 담은것
		
		HashMap<String,String> fileUrlMap = new HashMap<>();
		Gson gson= new Gson();
		fileUrlMap.put("url", uploadPath);// hasmap에서 넣을땐 put으로 / json의 파일은 url에 값으로 찍힌다
		String fileUrl = gson.toJson(fileUrlMap);// fileUrlMap을 gson으로 바꿔주기
		request.setAttribute("fileUrl", fileUrl); // 실어서 보내주기
		RequestDispatcher dispatcher = request.getRequestDispatcher("summernote_file_upload.jsp");
		dispatcher.forward(request, response);
	}
}



