package com.jjang051.controller.board;

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
import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class SummerNoteFileUploadController  implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "board/summernote_file_upload";
		ModelAndView mav = new ModelAndView(nextPage);
		
		String savePath = "summernote_upload";
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(savePath);
	
		File dir = new File(realPath);
		if(!dir.exists()) dir.mkdir();
		int fileSize = 1024*1024*100;
		String encoding = "UTF-8";
		DefaultFileRenamePolicy filePolicy = new DefaultFileRenamePolicy();
		MultipartRequest multipart =  new MultipartRequest(request, realPath, fileSize, encoding,filePolicy);
		
		String fileRealName = multipart.getFilesystemName("uploadFile");
		
		String uploadPath = savePath+"/"+fileRealName;
		
		
		System.out.println("uploadPath==="+uploadPath);
		
		HashMap<String,String> fileUrlMap = new HashMap<>();
		Gson gson= new Gson();
		fileUrlMap.put("url", uploadPath); 
		String fileUrl = gson.toJson(fileUrlMap);
		mav.addObject("fileUrl", fileUrl);
		return mav;
	}
}













